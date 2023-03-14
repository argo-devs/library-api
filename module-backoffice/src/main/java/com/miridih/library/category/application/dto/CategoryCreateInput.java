package com.miridih.library.category.application.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class CategoryCreateInput {
    private String categoryName;
}
