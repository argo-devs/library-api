package com.miridih.library.book.ui.response;

import com.miridih.library.book.application.dto.ExternalBookMeta;
import lombok.Data;

@Data
public class ExternalBookMetaResponse {

    private String title;
    private String description;
    private String author;
    private String publisher;
    private String imageUrl;

    public static ExternalBookMetaResponse from(ExternalBookMeta bookMeta) {
        ExternalBookMetaResponse response = new ExternalBookMetaResponse();
        response.title = bookMeta.getTitle();
        response.description = bookMeta.getDescription();
        response.author = bookMeta.getAuthor();
        response.publisher = bookMeta.getPublisher();
        response.imageUrl = bookMeta.getImageUrl();

        return response;
    }
}
