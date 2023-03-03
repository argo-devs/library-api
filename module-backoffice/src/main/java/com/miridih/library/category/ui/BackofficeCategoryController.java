package com.miridih.library.category.ui;

import com.miridih.library.category.application.BackofficeCategoryService;
import com.miridih.library.category.domain.Category;
import com.miridih.library.category.ui.request.CategoryCreateRequest;
import com.miridih.library.category.ui.request.CategoryUpdateRequest;
import com.miridih.library.category.ui.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeCategoryController {

    private final BackofficeCategoryService backofficeCategoryService;

    @GetMapping("/category")
    public List<CategoryResponse> getAllCategories(@RequestParam(required = false) String name) {
        List<Category> categoryList = new ArrayList<>();

        if(name != null) {
            Category category = backofficeCategoryService.findCategory(name);
            categoryList.add(category);
        } else {
            categoryList.addAll(backofficeCategoryService.findAllCategory());
        }

        return categoryList
                .stream()
                .map(CategoryResponse::from)
                .collect(Collectors.toList());
    }

    @PostMapping("/category")
    public CategoryResponse createCategory(@RequestBody CategoryCreateRequest request) {
        Category category = backofficeCategoryService.createCategory(request.toCategoryCreateInput());

        return CategoryResponse.from(category);
    }

    @PutMapping("/category")
    public CategoryResponse updateCategory(@RequestBody CategoryUpdateRequest request) {
        Category udpatedCategory = backofficeCategoryService.updateCategory(request.toCategoryUpdateInput());

        return CategoryResponse.from(udpatedCategory);
    }

    @DeleteMapping("/category/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        backofficeCategoryService.deleteCategory(categoryId);
    }
}
