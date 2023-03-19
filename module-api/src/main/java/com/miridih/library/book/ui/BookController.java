package com.miridih.library.book.ui;

import com.miridih.library.book.application.BookApiService;
import com.miridih.library.book.domain.Book;
import com.miridih.library.book.ui.response.BookResponse;
import com.miridih.library.core.ui.response.ApiResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookApiService bookApiService;

    @GetMapping("/book/{bookId}")
    public ApiResponse<BookResponse> searchBook(@PathVariable Long bookId) {
        log.info("BOOK:SRCH:RQST: 도서 조회 요청");

        try {
            Book book = bookApiService.getBook(bookId);

            return ApiResponse.of(BookResponse.from(book));
        } catch (Exception e) {
            log.error("BOOK:SRCH:FAIL: 도서 조회중 오류 발생.", e);
            return ApiResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }
}
