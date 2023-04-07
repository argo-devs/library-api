package com.miridih.library.book.ui.request;

import com.miridih.library.book.application.dto.BookMetaInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookMetaCreateRequest {
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

    @Schema(description = "ISBN", example = "9123812938")
    @NotBlank
    private String isbn;

    @Schema(description = "도서 이미지 링크", example = "https://image.com")
    private String imageUrl;

    @Schema(description = "수량", example = "1", defaultValue = "1")
    private int quantity = 1; // default

    @Schema(description = "카테고리 ID", example = "1")
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
