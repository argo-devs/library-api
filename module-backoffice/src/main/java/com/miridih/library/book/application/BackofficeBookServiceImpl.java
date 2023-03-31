package com.miridih.library.book.application;

import com.google.zxing.WriterException;
import com.miridih.library.book.application.code.BookCode;
import com.miridih.library.book.application.code.QRCodeService;
import com.miridih.library.book.domain.Book;
import com.miridih.library.book.exception.BookNotFoundException;
import com.miridih.library.book.infrastructure.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BackofficeBookServiceImpl implements BackofficeBookService {

    private final BookRepository bookRepository;
    private final QRCodeService qrCodeService;

    private Book getBookById(Long bookId) {
        return bookRepository
                .findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("도서를 찾을 수 없습니다.", bookId));
    }

    @Override
    public BookCode getCode(Long bookId) throws IOException, WriterException {
        Book book = getBookById(bookId);

        return qrCodeService.getCode(book.getId());
    }

    @Override
    public Book getBook(Long bookId) {
        return getBookById(bookId);
    }

    @Transactional
    @Override
    public Book registerBook(Long bookMetaId) {
        Book book = Book.from(bookMetaId);

        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteBook(Long bookId) {
        Book book = getBookById(bookId);
        book.isDeletable();

        bookRepository.delete(book);
    }

    @Transactional
    @Override
    public void deactivateBook(Long bookId) {
        Book book = getBookById(bookId);
        book.deactivate();

        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void activateBook(Long bookId) {
        Book book = getBookById(bookId);
        book.activate();

        bookRepository.save(book);
    }
}
