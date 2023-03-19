package com.miridih.library.book.infrastructure.naverbook;

import com.miridih.library.book.domain.ExternalBookMeta;
import lombok.Getter;

@Getter
class NaverBook {
    private String title;
    private String description;
    private String image;
    private String author;
    private String publisher;
    private String isbn;
    private String link;

    public ExternalBookMeta toExternalBookMeta() {
        return ExternalBookMeta
                .builder()
                .title(title)
                .description(description)
                .author(author)
                .publisher(publisher)
                .isbn(isbn)
                .imageUrl(image)
                .link(link)
                .build();
    }
}
