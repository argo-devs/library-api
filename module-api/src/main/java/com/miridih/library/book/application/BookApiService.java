package com.miridih.library.book.application;

import com.miridih.library.book.domain.Book;

public interface BookApiService {
    Book getBook(Long bookId);
}
