package com.miridih.library.book.application;

import com.google.zxing.WriterException;
import com.miridih.library.book.application.dto.BookMetaInfo;
import com.miridih.library.book.application.dto.BookMetaInput;
import com.miridih.library.book.application.code.BookCode;
import com.miridih.library.book.application.code.QRCodeService;
import com.miridih.library.book.application.dto.BookMetaSearchCondition;
import com.miridih.library.book.application.dto.ExternalBookMetaSearchCondition;
import com.miridih.library.book.domain.Book;
import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.book.domain.ExternalBookMeta;
import com.miridih.library.book.exception.BookMetaException;
import com.miridih.library.book.exception.BookMetaNotFoundException;
import com.miridih.library.book.infrastructure.BookMetaRepository;
import com.miridih.library.book.infrastructure.BookRepository;
import com.miridih.library.book.infrastructure.ExternalBookMetaAgent;
import com.miridih.library.category.domain.Category;
import com.miridih.library.category.exception.CategoryNotFoundException;
import com.miridih.library.category.infrastructure.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackofficeBookMetaServiceImpl implements BackofficeBookMetaService {

    private static final String DEFAULT_CATEGORY = "기타";

    private final BookRepository bookRepository;
    private final BookMetaRepository bookMetaRepository;
    private final CategoryRepository categoryRepository;
    private final ExternalBookMetaAgent externalBookMetaAgent;

    private final QRCodeService qrCodeService;

    @Override
    public Page<ExternalBookMeta> searchExternalBookMeta(ExternalBookMetaSearchCondition searchCondition) {
        return externalBookMetaAgent.search(searchCondition.getQuery(), searchCondition.getPageable());
    }

    @Override
    public List<BookCode> getAllBookCode(Long bookMetaId) {
        BookMeta bookMeta = getBookMetaById(bookMetaId);
        List<Book> bookList = bookMeta.getBookList();

        List<BookCode> bookCodeList = new ArrayList<>();
        for(Book book: bookList) {
            try {
                BookCode bookCode = qrCodeService.getCode(book.getId());
                bookCodeList.add(bookCode);
            } catch (IOException | WriterException e) {
                throw new BookMetaException("QR 코드 생성 실패", e, book);
            }
        }

        return bookCodeList;
    }

    @Override
    public BookMetaInfo searchBookMeta(BookMetaSearchCondition searchCondition) {
        if(searchCondition.isSearchingIndividualBookMeta()) {
            List<BookMeta> bookMetaList = bookMetaRepository
                    .findAllById(Collections.singleton(searchCondition.getBookMetaId()));

            return BookMetaInfo.of(new PageImpl<>(bookMetaList), categoryRepository.findAll());
        }

        Page<BookMeta> bookMetaPage;
        if(searchCondition.isEmptySearch()) {
            bookMetaPage = bookMetaRepository.findAll(searchCondition.getPageable());
        } else if(searchCondition.containsAllSearchCondition()) {
            bookMetaPage = bookMetaRepository
                    .findAllByTitleContainingAndCategory(
                            searchCondition.getName(),
                            searchCondition.getCategory(),
                            searchCondition.getPageable()
                    );
        } else {
            bookMetaPage = bookMetaRepository
                    .findAllByTitleContainingOrCategory(
                            searchCondition.getName(),
                            searchCondition.getCategory(),
                            searchCondition.getPageable()
                    );
        }

        return BookMetaInfo.of(bookMetaPage, categoryRepository.findAll());
    }

    @Transactional
    @Override
    public BookMeta registerBookMeta(BookMetaInput bookMetaInput) {
        Long categoryId = bookMetaInput.getCategoryId();
        if(categoryId == null) {
            Category category = categoryRepository
                    .findByName(DEFAULT_CATEGORY)
                    .orElseThrow(() -> new CategoryNotFoundException("카테고리를 찾을 수 없습니다.", DEFAULT_CATEGORY));

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

        bookMeta = bookMetaRepository.save(bookMeta);

        // 도서 등록
        List<Book> bookList = new ArrayList<>();
        for(int i = 0; i < bookMetaInput.getQuantity(); i++) {
            bookList.add(Book.from(bookMeta.getId()));
        }

        bookList = bookRepository.saveAll(bookList);
        bookMeta.addAllBooks(bookList);

        return bookMeta;
    }

    @Transactional
    @Override
    public void deleteBookMeta(Long bookMetaId) {
        // FK constraint 인 경우 에러 처리
        BookMeta bookMeta = getBookMetaById(bookMetaId);

        bookRepository.deleteAllInBatch(bookMeta.getBookList());
        bookMetaRepository.delete(bookMeta);
    }

    private BookMeta getBookMetaById(Long bookMetaId) {
        return bookMetaRepository
                .findById(bookMetaId)
                .orElseThrow(() -> new BookMetaNotFoundException("도서 메타를 찾을 수 없습니다.", bookMetaId));
    }
}
