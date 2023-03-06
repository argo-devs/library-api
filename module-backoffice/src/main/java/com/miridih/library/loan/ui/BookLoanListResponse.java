package com.miridih.library.loan.ui;

import com.miridih.library.loan.domain.BookLoan;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookLoanListResponse {
    private int totalPage;
    private List<BookLoanResponse> loanedBookList = new ArrayList<>();

    public static BookLoanListResponse from(Page<BookLoan> bookLoanPage) {
        BookLoanListResponse response = new BookLoanListResponse();
        response.totalPage = bookLoanPage.getTotalPages();
        response.loanedBookList = bookLoanPage.getContent()
                .stream()
                .map(BookLoanResponse::from)
                .collect(Collectors.toList());

        return response;
    }
}
