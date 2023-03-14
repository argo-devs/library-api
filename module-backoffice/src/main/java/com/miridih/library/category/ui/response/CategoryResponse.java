package com.miridih.library.category.ui.response;

import com.miridih.library.category.domain.Category;
import lombok.Data;

@Data
public class CategoryResponse {
    private Long categoryId;
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
