package com.miridih.library.book.ui;

import com.miridih.library.book.application.BackofficeBookService;
import com.miridih.library.book.application.dto.BookCode;
import com.miridih.library.book.domain.Book;
import com.miridih.library.book.ui.request.BookRegisterRequest;
import com.miridih.library.book.ui.response.BookCodeResponse;
import com.miridih.library.book.ui.response.BookResponse;
import com.miridih.library.core.ui.response.BackofficeResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeBookController {

    private final BackofficeBookService backofficeBookService;

    @GetMapping("/book/{bookId}")
    public BackofficeResponse<BookResponse> book(@PathVariable Long bookId) {
        log.info("BOOK:SRCH:RQST: 도서 조회 요청. [book={}]", bookId);

        try {
            Book book = backofficeBookService.getBook(bookId);

            return BackofficeResponse.of(BookResponse.from(book));
        } catch (Exception e) {
            log.error("BOOK:SRCH:FAIL: 도서 조회중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @PostMapping("/book")
    public BackofficeResponse<BookResponse> registerBook(@RequestBody BookRegisterRequest request) {
        log.info("BOOK:RGST:RQST: 도서 등록 요청. [request={}]", request);

        try {
            Book book = backofficeBookService.registerBook(request.getBookMetaId());

            return BackofficeResponse.of(BookResponse.from(book));
        } catch (Exception e) {
            log.error("BOOK:RGST:FAIL: 도서 등록중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }

    }

    @DeleteMapping("/book/{bookId}")
    public BackofficeResponse<BookResponse> deleteBook(@PathVariable Long bookId) {
        log.info("BOOK:DEL_:RQST: 도서 삭제 요청. [book={}]", bookId);

        try {
            backofficeBookService.deleteBook(bookId);

            return BackofficeResponse.of(BookResponse.of(bookId));
        } catch (Exception e) {
            log.error("BOOK:DEL_:FAIL: 도서 삭제중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @PutMapping("/book/status/{bookId}")
    public BackofficeResponse<BookResponse> activateBook(@PathVariable Long bookId) {
        log.info("BOOK:ACTV:RQST: 도서 활성화 요청. [book={}]", bookId);

        try {
            backofficeBookService.activateBook(bookId);

            return BackofficeResponse.of(BookResponse.of(bookId));
        } catch (Exception e) {
            log.error("BOOK:ACTV:FAIL: 도서 활성화중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @DeleteMapping("/book/status/{bookId}")
    public BackofficeResponse<BookResponse> deactivateBook(@PathVariable Long bookId) {
        log.info("BOOK:DATV:RQST: 도서 비활성화 요청. [book={}]", bookId);
        try {
            backofficeBookService.deactivateBook(bookId);

            return BackofficeResponse.of(BookResponse.of(bookId));
        } catch (Exception e) {
            log.error("BOOK:DATV:FAIL: 도서 비활성화중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @GetMapping("/book/code/{bookId}")
    public BackofficeResponse<BookCodeResponse> getQRCode(@PathVariable Long bookId) {
        log.info("BOOK:CODE:RQST: 도서 QR 코드 발급 요청. [book={}]", bookId);

        try {
            BookCode bookCode = backofficeBookService.getCode(bookId);

            return BackofficeResponse.of(BookCodeResponse.from(bookCode));
        } catch (Exception e) {
            log.error("BOOK:CODE:FAIL: 도서 QR 코드 발급중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }
}
