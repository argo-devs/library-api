package com.miridih.library.category.ui.request;

import com.miridih.library.category.application.dto.CategoryUpdateInput;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CategoryUpdateRequest {
    private Long categoryId;
    private String categoryName;

    public CategoryUpdateInput toCategoryUpdateInput() {
        return CategoryUpdateInput
                .builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
    }
}
