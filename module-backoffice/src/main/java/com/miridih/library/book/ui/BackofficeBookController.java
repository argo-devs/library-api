package com.miridih.library.book.ui;

import com.google.zxing.WriterException;
import com.miridih.library.book.application.BackofficeBookService;
import com.miridih.library.book.application.dto.BookCode;
import com.miridih.library.book.internal.domain.Book;
import com.miridih.library.book.ui.request.BookRegisterRequest;
import com.miridih.library.book.ui.response.BookCodeResponse;
import com.miridih.library.book.ui.response.BookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeBookController {

    private final BackofficeBookService backofficeBookService;

    @GetMapping("/book/{bookId}")
    public BookResponse book(@PathVariable Long bookId) {
        Book book = backofficeBookService.getBook(bookId);

        return BookResponse.from(book);
    }

    @PostMapping("/book")
    public BookResponse registerBook(@RequestBody BookRegisterRequest request) {
        Book book = backofficeBookService.registerBook(request.getBookMetaId());

        return BookResponse.from(book);
    }

    @DeleteMapping("/book/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        backofficeBookService.deleteBook(bookId);
    }

    @PutMapping("/book/status/{bookId}")
    public void activateBook(@PathVariable Long bookId) {
        backofficeBookService.activateBook(bookId);
    }

    @DeleteMapping("/book/status/{bookId}")
    public void deactivateBook(@PathVariable Long bookId) {
        backofficeBookService.deactivateBook(bookId);
    }

    @GetMapping("/book/code/{bookId}")
    public BookCodeResponse getQRCode(@PathVariable Long bookId) {
        try {
            BookCode bookCode = backofficeBookService.getCode(bookId);

            return BookCodeResponse.from(bookCode);
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }
    }
}
