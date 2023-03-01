package com.miridih.library.book.internal.application;

import com.miridih.library.book.internal.domain.Book;
import org.springframework.data.domain.Page;

public interface BookService {
    Book get(Long bookId);
    Page<Book> getAll();
    Book save(Book book);
    Book update(Book book);
    void delete(Long bookId);
}
