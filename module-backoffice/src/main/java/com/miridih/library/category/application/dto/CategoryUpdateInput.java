package com.miridih.library.category.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryUpdateInput {
    private Long categoryId;
    private String categoryName;
}
