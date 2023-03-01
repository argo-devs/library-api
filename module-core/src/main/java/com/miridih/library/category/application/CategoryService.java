package com.miridih.library.category.application;

import com.miridih.library.category.domain.Category;

import java.util.List;

public interface CategoryService {
    Category get(Long categoryId);
    List<Category> getAll();
    Category save(Category category);
    Category update(Category category);
    void delete(Long categoryId);
}
