package com.miridih.library.book.ui.response;

import com.miridih.library.book.application.dto.BookMetaInfo;
import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.category.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BookMetaListResponse {
    @Getter
    private static class CategoryInfo {
        @Schema(description = "카테고리 ID", example = "1")
        @NotBlank
        private Long categoryId;

        @Schema(description = "카테고리 이름", example = "개발")
        @NotBlank
        private String categoryName;

        @Schema(description = "카테고리에 포함된 도서 메타 수", defaultValue = "0", example = "5")
        @NotBlank
        private int totalCount;

        public static CategoryInfo from(Category category) {
            CategoryInfo categoryInfo = new CategoryInfo();
            categoryInfo.categoryId = category.getId();
            categoryInfo.categoryName = category.getDisplayName();
            categoryInfo.totalCount = category.getBookMetaCount();

            return categoryInfo;
        }
    }

    @Schema(description = "총 페이지 수", example = "1")
    @NotBlank
    private int totalPageNumber;

    @Schema(description = "현제 페이지", example = "0", defaultValue = "0")
    @NotBlank
    private int currentPageNumber;

    @Schema(description = "총 검색 결과 수", example = "10")
    @NotBlank
    private long currentElementCount;

    @Schema(description = "도서 전체 수", example = "100")
    @NotBlank
    private int totalBookMetaCount;

    @Schema(description = "도서 메타 리스트")
    @NotBlank
    private final List<BookMetaResponse> bookMetaList = new ArrayList<>();

    @Schema(description = "카테고리 정보 리스트")
    @NotBlank
    private final List<CategoryInfo> categoryInfoList = new ArrayList<>();

    public static BookMetaListResponse from(BookMetaInfo bookMetaInfo) {
        BookMetaListResponse response = new BookMetaListResponse();
        Page<BookMeta> bookMetaPage = bookMetaInfo.getContent();

        response.totalPageNumber = bookMetaPage.getTotalPages();
        response.currentPageNumber = bookMetaPage.getNumber();
        response.currentElementCount = bookMetaPage.getNumberOfElements();
        System.out.println("Total number of element: " + bookMetaPage.getTotalElements());

        bookMetaPage
                .getContent()
                .forEach(bookMeta -> response.bookMetaList.add(BookMetaResponse.from(bookMeta)));

        bookMetaInfo
                .getCategoryList()
                .forEach(category -> {
                    response.categoryInfoList.add(CategoryInfo.from(category));
                    response.totalBookMetaCount += category.getBookMetaCount();
                });

        return response;
    }
}
