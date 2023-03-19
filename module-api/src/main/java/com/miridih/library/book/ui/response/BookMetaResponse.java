package com.miridih.library.book.ui.response;

import com.miridih.library.book.domain.Book;
import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.book.domain.ExternalBookMeta;
import lombok.Getter;

@Getter
public class BookMetaResponse {

    private String title;
    private String author;
    private String publisher;
    private String link;
    private String category;
    private int totalBookCount;
    private long available;

    public static BookMetaResponse from(BookMeta bookMeta) {
        BookMetaResponse response = new BookMetaResponse();
        response.title = bookMeta.getTitle();
        response.author = bookMeta.getAuthor();
        response.publisher = bookMeta.getPublisher();
        response.category = bookMeta.getCategory().getDisplayName();
        response.totalBookCount = bookMeta.getBookList().size();
        response.available = bookMeta
                .getBookList()
                .stream()
                .filter(Book::isAvailableForLoan)
                .count();

        return response;
    }

    public static BookMetaResponse from(ExternalBookMeta bookMeta) {
        BookMetaResponse response = new BookMetaResponse();
        response.title = bookMeta.getTitle();
        response.author = bookMeta.getAuthor();
        response.publisher = bookMeta.getPublisher();
        response.link = bookMeta.getLink();

        return response;
    }
}
