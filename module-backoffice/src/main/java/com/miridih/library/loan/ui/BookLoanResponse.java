package com.miridih.library.loan.ui;

import com.miridih.library.loan.domain.BookLoan;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@ToString
public class BookLoanResponse {
    @Schema(description = "대출 ID", example = "5")
    @NotBlank
    private Long bookLoanId;

    @Schema(description = "대출 날짜", example = "2023-04-06T20:50:18.893457")
    @NotBlank
    private LocalDateTime loanedDate;

    public static BookLoanResponse from(BookLoan bookLoan) {
        BookLoanResponse response = new BookLoanResponse();
        response.bookLoanId = bookLoan.getId();
        response.loanedDate = bookLoan.getLoanedDate();

        return response;
    }

    public static BookLoanResponse of(Long bookLoanId) {
        BookLoanResponse response = new BookLoanResponse();
        response.bookLoanId = bookLoanId;

        return response;
    }
}
