package com.miridih.library.book.application;

import com.miridih.library.book.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookApiServiceImpl implements BookApiService {

    private final BookService bookService;

    @Override
    public Book getBook(Long bookId) {
        return bookService.getById(bookId);
    }
}
