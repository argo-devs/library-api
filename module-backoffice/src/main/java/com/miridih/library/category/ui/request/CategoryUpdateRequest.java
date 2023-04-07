package com.miridih.library.category.ui.request;

import com.miridih.library.category.application.dto.CategoryUpdateInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryUpdateRequest {
    @Schema(description = "카테고리 ID", example = "1")
    @NotBlank
    private Long categoryId;

    @Schema(description = "카테고리 이름", example = "개발")
    @NotBlank
    private String categoryName;

    public CategoryUpdateInput toCategoryUpdateInput() {
        return CategoryUpdateInput
                .builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
    }
}
