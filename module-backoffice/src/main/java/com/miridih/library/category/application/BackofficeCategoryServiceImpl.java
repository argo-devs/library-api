package com.miridih.library.category.application;

import com.miridih.library.category.application.dto.CategoryCreateInput;
import com.miridih.library.category.application.dto.CategoryUpdateInput;
import com.miridih.library.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BackofficeCategoryServiceImpl implements BackofficeCategoryService {

    private static final String DEFAULT_BOOK_CATEGORY_VALUE = "기타";

    private final CategoryService categoryService;

    @Override
    public Category findCategory(String categoryName) {
        final List<Category> categoryList = categoryService.getAll();

        return categoryList
                .stream()
                .filter(category
                        -> categoryName.equals(category.getName())
                        && categoryName.equals(category.getDisplayName()))
                .findAny()
                .orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryService.getAll();
    }

    @Override
    public Category createCategory(CategoryCreateInput input) {
        return categoryService.save(Category.of(input.getCategoryName()));
    }

    @Override
    public Category updateCategory(CategoryUpdateInput input) {
        return categoryService.update(Category.of(input.getCategoryId(), input.getCategoryName()));
    }

    @Override
    public void deleteCategory(long categoryId) {
        Category category = categoryService.get(categoryId);
        category.changeDisplayName(DEFAULT_BOOK_CATEGORY_VALUE); // 카테고리 '가타'로 변경

        categoryService.update(category);
    }
}
