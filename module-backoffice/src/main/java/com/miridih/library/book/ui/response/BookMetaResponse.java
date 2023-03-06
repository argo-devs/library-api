package com.miridih.library.book.ui.response;

import com.miridih.library.book.application.dto.ExternalBookMeta;
import com.miridih.library.book.internal.domain.BookMeta;
import lombok.Data;

import java.util.List;

@Data
public class BookMetaResponse {
    @Data
    private static class Category {
        private Long id;
        private String name;

        private static Category of(Long id, String name) {
            Category response = new Category();
            response.id = id;
            response.name = name;

            return  response;
        }
    }

    private Long id;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private String isbn;
    private String imageUrl;
    private Category category;
    private List<BookResponse> bookList;

    public static BookMetaResponse from(BookMeta bookMeta) {
        BookMetaResponse response = new BookMetaResponse();
        response.id = bookMeta.getId();
        response.title = bookMeta.getTitle();
        response.description = bookMeta.getDescription();
        response.author = bookMeta.getAuthor();
        response.publisher = bookMeta.getPublisher();
        response.isbn = bookMeta.getIsbn();
        response.imageUrl = bookMeta.getImageUrl();
        response.category = Category.of(bookMeta.getCategory().getId(), bookMeta.getCategory().getDisplayName());

        // TODO: set bookList


        return response;
    }

    public static BookMetaResponse from(ExternalBookMeta externalBookMeta) {
        BookMetaResponse response = new BookMetaResponse();
        response.title = externalBookMeta.getTitle();
        response.description = externalBookMeta.getDescription();
        response.author = externalBookMeta.getAuthor();
        response.publisher = externalBookMeta.getPublisher();
        response.isbn = externalBookMeta.getIsbn();
        response.imageUrl = externalBookMeta.getImageUrl();

        return response;
    }
}
