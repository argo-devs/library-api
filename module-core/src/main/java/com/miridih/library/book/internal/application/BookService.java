package com.miridih.library.book.internal.application;

import com.miridih.library.book.internal.domain.Book;

import java.util.List;

public interface BookService {
    Book get(Long bookId);
    Book save(Book book);
    List<Book> bulkSave(List<Book> bookList);
    Book update(Book book);
    void delete(Long bookId);
}
