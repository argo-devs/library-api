package com.miridih.library.book.ui.response;

import com.miridih.library.book.application.dto.ExternalBookMeta;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class ExternalBookMetaListResponse {
    private int totalPage;
    private List<ExternalBookMetaResponse> bookMetaList = new ArrayList<>();

    public static ExternalBookMetaListResponse from(List<ExternalBookMeta> externalBookMetaList) {
        ExternalBookMetaListResponse response = new ExternalBookMetaListResponse();
        response.totalPage = 0;
        externalBookMetaList
                .forEach(bookMeta ->
                        response.bookMetaList.add(ExternalBookMetaResponse.from(bookMeta))
                );

        return response;
    }
}
