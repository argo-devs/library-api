package com.miridih.library.loan.ui;

import com.miridih.library.loan.domain.BookLoan;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookLoanResponse {

    private LocalDateTime loanedDate;

    public static BookLoanResponse from(BookLoan bookLoan) {
        BookLoanResponse response = new BookLoanResponse();
        response.loanedDate = bookLoan.getLoanedDate();

        return response;
    }
}
