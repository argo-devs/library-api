package com.miridih.library.book.ui.response;

import com.miridih.library.book.domain.BookMeta;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BookMetaResponse {
    @Getter
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

    private Long bookMetaId;
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
        response.bookMetaId = bookMeta.getId();
        response.title = bookMeta.getTitle();
        response.description = bookMeta.getDescription();
        response.author = bookMeta.getAuthor();
        response.publisher = bookMeta.getPublisher();
        response.isbn = bookMeta.getIsbn();
        response.imageUrl = bookMeta.getImageUrl();
        response.category = Category.of(bookMeta.getCategory().getId(), bookMeta.getCategory().getDisplayName());
        response.bookList = bookMeta
                .getBookList()
                .stream()
                .map(BookResponse::from)
                .collect(Collectors.toList());

        return response;
    }

    public static BookMetaResponse of(Long bookMetaId) {
        BookMetaResponse response = new BookMetaResponse();
        response.bookMetaId = bookMetaId;

        return response;
    }
}
