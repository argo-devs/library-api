package com.miridih.library.book.ui.response;

import com.miridih.library.book.domain.ExternalBookMeta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ExternalBookMetaResponse {
    @Schema(description = "도서 제목", example = "알고리즘 101")
    @NotBlank
    private String title;

    @Schema(description = "도서 설명", example = "이 책은 알고리즘 책입니다.")
    @NotBlank
    private String description;

    @Schema(description = "저자", example = "미리리")
    @NotBlank
    private String author;

    @Schema(description = "출판사", example = "미리출판사")
    @NotBlank
    private String publisher;

    @Schema(description = "도서 이미지 링크", example = "https://image.com")
    private String imageUrl;

    @Schema(description = "도서 구매 링크", example = "https://shopping.com")
    @NotBlank
    private String link;

    public static ExternalBookMetaResponse from(ExternalBookMeta bookMeta) {
        ExternalBookMetaResponse response = new ExternalBookMetaResponse();
        response.title = bookMeta.getTitle();
        response.description = bookMeta.getDescription();
        response.author = bookMeta.getAuthor();
        response.publisher = bookMeta.getPublisher();
        response.imageUrl = bookMeta.getImageUrl();
        response.link = bookMeta.getLink();

        return response;
    }
}
