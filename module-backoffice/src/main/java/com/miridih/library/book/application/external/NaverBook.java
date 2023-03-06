package com.miridih.library.book.application.external;

import com.miridih.library.book.application.dto.ExternalBookMeta;
import lombok.Getter;

@Getter
class NaverBook {
    private String title;
    private String description;
    private String image;
    private String author;
    private String publisher;
    private String isbn;

    public ExternalBookMeta toExternalBookMeta() {
        return ExternalBookMeta
                .builder()
                .title(title)
                .description(description)
                .author(author)
                .publisher(publisher)
                .isbn(isbn)
                .imageUrl(image)
                .build();
    }
}
