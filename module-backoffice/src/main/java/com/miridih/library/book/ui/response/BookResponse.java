package com.miridih.library.book.ui.response;

import com.miridih.library.book.internal.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class BookResponse {
    @Getter
    @AllArgsConstructor
    private static class Loan {
        private Long bookLoanId;
    }

    private Long bookId;
    private String uuid;
    private String registeredDate;
    private String status;
    private Loan bookLoan;

    public static BookResponse from(Book book) {
        BookResponse response = new BookResponse();
        response.bookId = book.getId();
        response.uuid = book.getUuid().toString();
        response.registeredDate = book.getRegisteredDate().toLocalDate().toString();
        response.status = book.getStatus().name();

        if(book.getBookLoan() != null) {
            response.bookLoan = new Loan(book.getBookLoan().getId());
        }

        return response;
    }
}
