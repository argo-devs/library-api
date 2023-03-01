package com.miridih.library.category.ui.response;

import com.miridih.library.category.domain.Category;
import lombok.Builder;

@Builder
public class CategoryResponse {
    private Long categoryId;
    private String categoryName;

    public static CategoryResponse from(Category category) {
        return CategoryResponse
                .builder()
                .categoryId(category.getId())
                .categoryName(category.getDisplayName())
                .build();
    }
}
