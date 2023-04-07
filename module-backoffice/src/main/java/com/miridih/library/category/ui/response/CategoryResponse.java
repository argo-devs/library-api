package com.miridih.library.category.ui.response;

import com.miridih.library.category.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CategoryResponse {
    @Schema(description = "카테고리 ID", example = "1")
    private Long categoryId;

    @Schema(description = "카테고리 이름", example = "개발")
    private String categoryName;

    public static CategoryResponse from(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.categoryId = category.getId();
        response.categoryName = category.getDisplayName();

        return response;
    }

    public static CategoryResponse of(Long categoryId) {
        CategoryResponse response = new CategoryResponse();
        response.categoryId = categoryId;

        return response;
    }
}
