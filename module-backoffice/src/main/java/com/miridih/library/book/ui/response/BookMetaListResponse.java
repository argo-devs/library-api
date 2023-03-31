package com.miridih.library.book.ui.response;

import com.miridih.library.book.application.dto.BookMetaInfo;
import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.category.domain.Category;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BookMetaListResponse {
    @Getter
    private static class CategoryInfo {
        private Long categoryId; // 카테고리 ID
        private String categoryName; // 카테고리 이름
        private int totalCount; // 카테고리에 포함된 도서 메타 수

        public static CategoryInfo from(Category category) {
            CategoryInfo categoryInfo = new CategoryInfo();
            categoryInfo.categoryId = category.getId();
            categoryInfo.categoryName = category.getDisplayName();
            categoryInfo.totalCount = category.getBookMetaCount();

            return categoryInfo;
        }
    }

    private int totalPageNumber; // 총 페이지 수
    private int currentPageNumber; // 현재 페이지 (0-based)
    private long currentElementCount; // 총 검색 결과 수
    private int totalBookMetaCount; // 도서 전체 수
    private final List<BookMetaResponse> bookMetaList = new ArrayList<>();
    private final List<CategoryInfo> categoryInfoList = new ArrayList<>();

    public static BookMetaListResponse from(BookMetaInfo bookMetaInfo) {
        BookMetaListResponse response = new BookMetaListResponse();
        Page<BookMeta> bookMetaPage = bookMetaInfo.getContent();

        System.out.println("Total Elemetn: " + bookMetaPage.getTotalElements());
        response.totalPageNumber = bookMetaPage.getTotalPages();
        response.currentPageNumber = bookMetaPage.getNumber();
        response.currentElementCount = bookMetaPage.getNumberOfElements();

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
