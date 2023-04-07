package com.miridih.library.category.ui.request;

import com.miridih.library.category.application.dto.CategoryCreateInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryCreateRequest {
    @Schema(description = "카테고리 이름", example = "개발")
    @NotBlank
    private String categoryName;

    public CategoryCreateInput toCategoryCreateInput() {
        return CategoryCreateInput
                .builder()
                .categoryName(categoryName)
                .build();
    }
}
