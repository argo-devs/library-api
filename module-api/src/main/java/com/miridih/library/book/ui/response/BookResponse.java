package com.miridih.library.book.ui.response;

import com.miridih.library.book.domain.Book;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookResponse {
    @Getter
    private static class LoanInfo {
        private LocalDateTime loanedDate;
        private LocalDateTime toBeReturnedDate;

        public static LoanInfo of(LocalDateTime from, LocalDateTime to) {
            LoanInfo loan = new LoanInfo();
            loan.loanedDate = from;
            loan.toBeReturnedDate = to;

            return loan;
        }
    }

    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private String category;
    private int totalBookCount;
    private String status;
    private String image;
    private LoanInfo loanInfo;

    public static BookResponse from(Book book) {
        BookResponse response = new BookResponse();
        response.bookId = book.getId();
        response.title = book.getBookMeta().getTitle();
        response.author = book.getBookMeta().getAuthor();
        response.publisher = book.getBookMeta().getPublisher();
        response.category = book.getBookMeta().getCategory().getDisplayName();
        response.totalBookCount = book.getBookMeta().getBookList().size();
        response.status = book.getStatus().getValue();
        response.image = book.getBookMeta().getImageUrl(); // TODO: 이미지 저장방법 바꾸기

        if(book.isInLoan()) {
            response.loanInfo = LoanInfo.of(
                    book.getBookLoan().getLoanedDate(),
                    book.getBookLoan().getToBeReturnedDate()
            );
        }

        return response;

    }
}
