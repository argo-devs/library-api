package com.miridih.library.category.ui.response;

import com.miridih.library.category.domain.Category;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

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
}
