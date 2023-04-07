package com.miridih.library.loan.ui;

import com.miridih.library.core.ui.response.BackofficeResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
import com.miridih.library.loan.application.BackofficeBookLoanService;
import com.miridih.library.loan.domain.BookLoan;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "도서 대출 API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeBookLoanController {

    private final BackofficeBookLoanService backofficeBookLoanService;

    @Operation(summary = "대출 조회")
    @GetMapping("/book/loan")
    public BackofficeResponse<BookLoanListResponse> getAllLoanBooks(@ParameterObject Pageable pageable) {
        log.info("LOAN:SRCH:RQST: 대출 조회 요청. [pageable={}]", pageable);

        try {
            Page<BookLoan> bookLoanPage = backofficeBookLoanService.getAllLoanedBook(pageable);

            return BackofficeResponse.of(BookLoanListResponse.from(bookLoanPage));
        } catch (Exception e) {
            log.error("LOAN:SRCH:FAIL: 대출 조회중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }
    }

    @Operation(summary = "도서 반납")
    @DeleteMapping("/book/loan/{bookLoanId}")
    public BackofficeResponse<BookLoanResponse> updateBookLoan(
            @Parameter(description = "대출 ID") @PathVariable Long bookLoanId) {
        log.info("LOAN:DEL_:RQST: 도서 반납 요청. [loan={}]", bookLoanId);

        try {
            backofficeBookLoanService.returnBook(bookLoanId);

            return BackofficeResponse.of(BookLoanResponse.of(bookLoanId));
        } catch (Exception e) {
            log.error("LOAN:DEL_:FAIL: 도서 반납중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }
    }
}
