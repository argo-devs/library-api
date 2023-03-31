package com.miridih.library.category.ui;

import com.miridih.library.category.application.BackofficeCategoryService;
import com.miridih.library.category.domain.Category;
import com.miridih.library.category.exception.CategoryException;
import com.miridih.library.category.ui.request.CategoryCreateRequest;
import com.miridih.library.category.ui.request.CategoryUpdateRequest;
import com.miridih.library.category.ui.response.CategoryResponse;
import com.miridih.library.core.ui.response.BackofficeResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
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
    public BackofficeResponse<List<CategoryResponse>> getAllCategories(@RequestParam(required = false) String name) {
        log.info("CATE:SRCH:RQST: 카테고리 조회 요청. [request={}]", name);

        List<Category> categoryList = new ArrayList<>();

        try {
            if(name != null) {
                Category category = backofficeCategoryService.findCategory(name);
                categoryList.add(category);
            } else {
                categoryList.addAll(backofficeCategoryService.findAllCategory());
            }

            List<CategoryResponse> response =  categoryList
                    .stream()
                    .map(CategoryResponse::from)
                    .collect(Collectors.toList());

            return BackofficeResponse.of(response);
        } catch (CategoryException e) {
            log.warn("CATE:SRCH:FAIL: 카테고리 조회중 오류 발생.", e);
            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        } catch (Exception e) {
            log.error("CATE:SRCH:FAIL: 카테고리 조회중 오류 발생.", e);
            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }
    }

    @PostMapping("/category")
    public BackofficeResponse<CategoryResponse> createCategory(@RequestBody CategoryCreateRequest request) {
        log.info("CATE:CRTE:RQST: 카테고리 생성 요청. [request={}]", request);

        try {
            Category category = backofficeCategoryService.createCategory(request.toCategoryCreateInput());

            return BackofficeResponse.of(CategoryResponse.from(category));
        } catch (CategoryException e) {
            log.warn("CATE:CRTE:FAIL: 카테고리 생성중 오류 발생.", e);
            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        } catch (Exception e) {
            log.error("CATE:CRTE:FAIL: 카테고리 생성중 오류 발생.", e);
            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }
    }

    @PutMapping("/category")
    public BackofficeResponse<CategoryResponse> updateCategory(@RequestBody CategoryUpdateRequest request) {
        log.info("CATE:UPDT:RQST: 카테고리 변경 요청. [request={}]", request);

        try {
            Category udpatedCategory = backofficeCategoryService.updateCategory(request.toCategoryUpdateInput());

            return BackofficeResponse.of(CategoryResponse.from(udpatedCategory));
        } catch (CategoryException e) {
            log.warn("CATE:UPDT:FAIL: 카테고리 변경 처리중 오류 발생.", e);
            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        } catch (Exception e) {
            log.error("CATE:UPDT:FAIL: 카테고리 변경 처리중 오류 발생.", e);
            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }
    }

    @DeleteMapping("/category/{categoryId}")
    public BackofficeResponse<CategoryResponse> deleteCategory(@PathVariable Long categoryId) {
        log.info("CATE:DEL_:RQST: 카테고리 삭제 요청. [categoryId={}]", categoryId);

        try {
            backofficeCategoryService.deleteCategory(categoryId);

            return BackofficeResponse.of(CategoryResponse.of(categoryId));
        } catch (CategoryException e) {
            log.warn("CATE:DEL_:FAIL: 카테고리 삭제중 오류 발생.", e);
            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }  catch (Exception e) {
            log.error("CATE:DEL_:FAIL: 카테고리 삭제중 오류 발생.", e);
            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }
    }
}
