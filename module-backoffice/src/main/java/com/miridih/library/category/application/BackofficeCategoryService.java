package com.miridih.library.category.application;

import com.miridih.library.category.application.dto.CategoryCreateInput;
import com.miridih.library.category.application.dto.CategoryUpdateInput;
import com.miridih.library.category.domain.Category;

import java.util.List;

public interface BackofficeCategoryService {
    Category findCategory(String categoryName);
    List<Category> findAllCategory();
    Category createCategory(CategoryCreateInput input);
    Category updateCategory(CategoryUpdateInput input);
    void deleteCategory(Long categoryId);
}
