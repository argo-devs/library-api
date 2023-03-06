package com.miridih.library.book.ui.request;

import com.miridih.library.book.application.dto.BookMetaInput;
import lombok.Data;

@Data
public class BookMetaUpdateRequest {
    private Long id;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private String isbn;
    private String imageUrl;
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
                .categoryId(categoryId)
                .build();
    }
}
