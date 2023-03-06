package com.miridih.library.book.ui.request;

import com.miridih.library.book.application.dto.BookMetaInput;
import lombok.Data;
import lombok.NonNull;

@Data
public class BookMetaCreateRequest {
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private String author;
    @NonNull
    private String publisher;
    @NonNull
    private String isbn;
    private String imageUrl;
    private int quantity = 1; // default
    private Long categoryId;

    public BookMetaInput toBookMetaInput() {
        return BookMetaInput
                .builder()
                .title(title)
                .description(description)
                .author(author)
                .publisher(publisher)
                .isbn(isbn)
                .imageUrl(imageUrl)
                .quantity(quantity)
                .categoryId(categoryId)
                .build();
    }
}
