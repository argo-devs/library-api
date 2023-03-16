package com.miridih.library.book.application;

import com.google.zxing.WriterException;
import com.miridih.library.book.application.dto.*;
import com.miridih.library.book.application.external.ExternalBookMetaService;
import com.miridih.library.book.internal.application.BookMetaService;
import com.miridih.library.book.internal.application.BookService;
import com.miridih.library.book.internal.domain.Book;
import com.miridih.library.book.internal.domain.BookMeta;
import com.miridih.library.category.application.CategoryService;
import com.miridih.library.category.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackofficeBookMetaServiceImpl implements BackofficeBookMetaService {

    private final CategoryService categoryService;
    private final BookService bookService;
    private final BookMetaService bookMetaService;
    private final ExternalBookMetaService externalBookMetaService;
    private final BackofficeBookService backofficeBookService;

    private static final String DEFAULT_CATEGORY = "기타";

    @Override
    public List<ExternalBookMeta> searchBookMeta(ExternalBookMetaSearchCondition searchCondition) {
        return externalBookMetaService.search(searchCondition);
    }

    @Override
    public List<BookCode> getAllBookCode(Long bookMetaId) {
        BookMeta bookMeta = bookMetaService.getById(bookMetaId);
        List<Book> bookList = bookMeta.getBookList();
        List<BookCode> bookCodeList = new ArrayList<>();
        for(Book book: bookList) {
            try {
                BookCode bookCode = backofficeBookService.getCode(book.getId());
                bookCodeList.add(bookCode);
            } catch (IOException | WriterException e) {
                // throw new RuntimeException("");
                log.error("");
            }
        }

        return bookCodeList;
    }

    @Override
    public Page<BookMeta> searchBookMeta(BookMetaSearchCondition searchCondition) {
        if(searchCondition.getBookMetaId() != null) {
            BookMeta bookMeta = bookMetaService.getById(searchCondition.getBookMetaId());
            return new PageImpl<>(List.of(bookMeta));
        } else if(searchCondition.getName() != null) {
            return bookMetaService.getByName(searchCondition.getName(), searchCondition.getPageable());
        }

        return bookMetaService.getAll(searchCondition.getPageable());
    }

    @Transactional
    @Override
    public BookMeta registerBookMeta(BookMetaInput bookMetaInput) {
        Long categoryId = bookMetaInput.getCategoryId();
        if(categoryId == null) {
            Category category = categoryService.getByName(DEFAULT_CATEGORY);
            categoryId = category.getId();
        }

        // 도서 메타 정보 등록
        BookMeta bookMeta = BookMeta.of(
                bookMetaInput.getTitle(),
                bookMetaInput.getDescription(),
                bookMetaInput.getAuthor(),
                bookMetaInput.getPublisher(),
                bookMetaInput.getIsbn(),
                bookMetaInput.getImageUrl(),
                categoryId
        );
        bookMeta = bookMetaService.save(bookMeta);

        // 도서 등록
        List<Book> bookList = new ArrayList<>();
        for(int i = 0; i < bookMetaInput.getQuantity(); i++) {
            bookList.add(Book.from(bookMeta.getId()));
        }

        bookService.bulkSave(bookList);

        return bookMeta;
    }

    @Override
    public BookMeta updateBookMeta(BookMetaInput bookMetaInput) {
        BookMeta bookMeta = bookMetaService.getById(bookMetaInput.getId());

        return null;
    }

    @Override
    public void deleteBookMeta(Long bookMetaId) {
        bookMetaService.delete(bookMetaId);
    }
}
