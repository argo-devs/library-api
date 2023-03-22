package com.miridih.library.category.application;

import com.miridih.library.category.application.dto.CategoryCreateInput;
import com.miridih.library.category.application.dto.CategoryUpdateInput;
import com.miridih.library.category.domain.Category;
import com.miridih.library.category.exception.CategoryException;
import com.miridih.library.category.exception.CategoryNotFoundException;
import com.miridih.library.category.exception.DuplicatedCategoryException;
import com.miridih.library.category.infrastructure.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackofficeCategoryServiceImpl implements BackofficeCategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findCategory(String categoryName) {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList
                .stream()
                .filter(category -> category.isEqualTo(categoryName))
                .findAny()
                .orElseThrow(() -> new CategoryNotFoundException("카테고리가 존재하지 않습니다.", categoryName));
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(CategoryCreateInput input) {
        String categoryName = input.getCategoryName();
        categoryRepository
                .findByName(categoryName)
                .ifPresent(category -> {
                    throw new DuplicatedCategoryException("이미 존재하는 카테고리 입니다.", categoryName);
                });

        return categoryRepository.save(Category.of(categoryName));
    }

    @Override
    public Category updateCategory(CategoryUpdateInput input) {
        findCategoryById(input.getCategoryId());

        return categoryRepository.save(Category.of(input.getCategoryId(), input.getCategoryName()));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = findCategoryById(categoryId);
        if(category.isDefault()) {
            throw new CategoryException("기타 카테고리는 삭제가 불가능합니다.", category);
        }
        if(category.isDeleted()) {
            throw new CategoryException("이미 삭제한 카테고리 입니다.", categoryId);
        }

        category.delete();
        categoryRepository.save(category);
    }

    private Category findCategoryById(Long categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("카테고리가 존재하지 않습니다.", categoryId));
    }
}
