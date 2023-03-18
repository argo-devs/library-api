package com.miridih.library.book.application;

import com.miridih.library.book.domain.Book;

import java.util.List;

public interface BookService {
    Book getById(Long bookId);
    Book save(Book book);
    List<Book> bulkSave(List<Book> bookList);
    Book update(Book book);
    void delete(Long bookId);
}
