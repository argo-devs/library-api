package com.miridih.library.book.ui.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookCreateRequest {
    @Schema(description = "도서 메타 ID", example = "1")
    @NotBlank
    private Long bookMetaId;
}
