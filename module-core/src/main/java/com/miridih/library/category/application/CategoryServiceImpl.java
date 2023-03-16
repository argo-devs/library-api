package com.miridih.library.category.application;

import com.miridih.library.category.domain.Category;
import com.miridih.library.category.exception.CategoryNotFoundException;
import com.miridih.library.category.exception.DuplicatedCategoryException;
import com.miridih.library.category.infrastructure.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getById(Long categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("카테고리가 존재하지 않습니다.", categoryId));
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository
                .findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("카테고리가 존재하지 않습니다.", name));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        categoryRepository
                .findByName(category.getName())
                .ifPresent(c -> {
                    throw new DuplicatedCategoryException("이미 존재하는 카테고리 입니다.", category);
                });

        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        getById(category.getId());

        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long categoryId) {
        getById(categoryId);

        categoryRepository.delete(Category.of(categoryId));
    }
}
