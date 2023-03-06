package com.miridih.library.book.internal.application;

import com.miridih.library.book.internal.domain.Book;
import com.miridih.library.book.internal.infrastructure.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book get(Long bookId) {
        return bookRepository
                .findById(bookId)
                .orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> bulkSave(List<Book> bookList) {
        return bookRepository.saveAll(bookList);
    }

    @Override
    public Book update(Book book) {
        return save(book);
    }

    @Override
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
