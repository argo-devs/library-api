package com.miridih.library.book.application;

import com.google.zxing.WriterException;
import com.miridih.library.book.application.dto.BookCode;
import com.miridih.library.book.domain.Book;

import java.io.IOException;

public interface BackofficeBookService {
    BookCode getCode(Long bookId) throws IOException, WriterException;
    Book getBook(Long bookId);
    Book registerBook(Long bookMetaId);
    void deleteBook(Long bookId);
    void deactivateBook(Long bookId);
    void activateBook(Long bookId);

}
