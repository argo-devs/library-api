package com.miridih.library.book.ui.request;

import com.miridih.library.book.application.dto.BookMetaInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookMetaUpdateRequest {
    @Schema(description = "도서 메타 ID", example = "1")
    @NotBlank
    private Long id;

    @Schema(description = "도서 제목", example = "자료구조 101")
    private String title;

    @Schema(description = "도서 설명", example = "이 책은 자료구조 책입니다.")
    private String description;

    @Schema(description = "저자", example = "미리리")
    private String author;

    @Schema(description = "출판사", example = "아고출판사")
    private String publisher;

    @Schema(description = "도서 이미지 링크", example = "https://url.com")
    private String imageUrl;

    @Schema(description = "카테고리 ID", example = "1")
    private Long categoryId;

    public BookMetaInput toBookMetaInput() {
        return BookMetaInput
                .builder()
                .title(title)
                .description(description)
                .author(author)
                .publisher(publisher)
                .imageUrl(imageUrl)
                .categoryId(categoryId)
                .build();
    }
}
