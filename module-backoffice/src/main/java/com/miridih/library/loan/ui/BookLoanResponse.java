package com.miridih.library.loan.ui;

import com.miridih.library.loan.domain.BookLoan;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class BookLoanResponse {

    private Long bookLoanId;
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
