package com.miridih.library.book.ui.response;

import com.miridih.library.book.domain.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class BookResponse {
    @Getter
    @AllArgsConstructor
    private static class Loan {

        @Schema(description = "대출 ID", example = "1")
        private Long bookLoanId;
    }

    @Schema(description = "도서 ID", example = "1")
    @NotBlank
    private Long bookId;

    @Schema(description = "도서 UUID", example = "1")
    @NotBlank
    private String uuid;

    @Schema(description = "도서 등록 날짜", example = "1")
    @NotBlank
    private String registeredDate;

    @Schema(description = "도서 상태 (대출여부/활성여부)", example = "ACTIVE")
    @NotBlank
    private String status;

    @Schema(description = "대출 정보", example = "1")
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

    public static BookResponse of(Long bookId) {
        BookResponse response = new BookResponse();
        response.bookId = bookId;

        return response;
    }
}
