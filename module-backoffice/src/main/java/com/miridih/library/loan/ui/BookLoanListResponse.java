package com.miridih.library.loan.ui;

import com.miridih.library.loan.domain.BookLoan;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class BookLoanListResponse {
    private int totalPage;
    private final List<BookLoanResponse> loanedBookList = new ArrayList<>();

    public static BookLoanListResponse from(Page<BookLoan> bookLoanPage) {
        BookLoanListResponse response = new BookLoanListResponse();
        response.totalPage = bookLoanPage.getTotalPages();
        bookLoanPage
                .getContent()
                .forEach(bookLoan ->
                        response.loanedBookList.add(BookLoanResponse.from(bookLoan))
                );

        return response;
    }
}
