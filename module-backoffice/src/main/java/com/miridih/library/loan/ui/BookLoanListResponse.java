package com.miridih.library.loan.ui;

import com.miridih.library.core.ui.response.PaginationResponse;
import com.miridih.library.loan.domain.BookLoan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class BookLoanListResponse {
    @Schema(description = "도서 대출 리스트")
    @NotBlank
    private final List<BookLoanResponse> loanedBookList = new ArrayList<>();

    @NotBlank
    private PaginationResponse pagination;

    public static BookLoanListResponse from(Page<BookLoan> bookLoanPage) {
        BookLoanListResponse response = new BookLoanListResponse();
        bookLoanPage
                .getContent()
                .forEach(bookLoan ->
                        response.loanedBookList.add(BookLoanResponse.from(bookLoan))
                );
        response.pagination = PaginationResponse.from(bookLoanPage);

        return response;
    }
}
