package com.miridih.library.book.ui.response;

import com.miridih.library.book.domain.ExternalBookMeta;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExternalBookMetaListResponse {
    private int totalPageNumber; // 총 페이지 수
    private int currentPageNumber; // 현재 페이지 (0-based)
    private long currentElementCount; // 총 검색 결과 수
    private int totalBookMetaCount; // 도서 전체 수
    private final List<ExternalBookMetaResponse> bookMetaList = new ArrayList<>();

    public static ExternalBookMetaListResponse from(Page<ExternalBookMeta> externalBookMetaPage) {
        ExternalBookMetaListResponse response = new ExternalBookMetaListResponse();
        response.totalPageNumber = externalBookMetaPage.getTotalPages();
        response.totalBookMetaCount = (int) externalBookMetaPage.getTotalElements();
        response.currentPageNumber = externalBookMetaPage.getNumber();
        response.currentElementCount = externalBookMetaPage.getNumberOfElements();
        List<ExternalBookMeta> externalBookMetaList = externalBookMetaPage.getContent();
        externalBookMetaList
                .forEach(bookMeta ->
                        response.bookMetaList.add(ExternalBookMetaResponse.from(bookMeta))
                );

        return response;
    }
}
