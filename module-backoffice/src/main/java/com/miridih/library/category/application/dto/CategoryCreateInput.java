package com.miridih.library.category.application.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryCreateInput {
    private String categoryName;
}
