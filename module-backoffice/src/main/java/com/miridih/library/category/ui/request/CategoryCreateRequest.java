package com.miridih.library.category.ui.request;

import com.miridih.library.category.application.dto.CategoryCreateInput;
import lombok.Data;

@Data
public class CategoryCreateRequest {
    private String categoryName;

    public CategoryCreateInput toCategoryCreateInput() {
        return CategoryCreateInput
                .builder()
                .categoryName(categoryName)
                .build();
    }
}
