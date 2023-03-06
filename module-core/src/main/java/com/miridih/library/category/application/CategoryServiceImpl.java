package com.miridih.library.category.application;

import com.miridih.library.category.domain.Category;
import com.miridih.library.category.infrastructure.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category get(Long categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository
                .findByName(name)
                .orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        return save(category);
    }

    @Override
    public void delete(Long categoryId) {
        if(categoryId == null) {
            throw new RuntimeException("");
        }

        categoryRepository.delete(Category.of(categoryId));
    }
}
