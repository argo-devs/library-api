package com.miridih.library.book.ui.response;

import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.book.domain.ExternalBookMeta;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BookMetaListResponse {
    private int totalPage;
    private final List<BookMetaResponse> bookMetaList = new ArrayList<>();

    public static BookMetaListResponse from(List<ExternalBookMeta> externalBookMetaList) {
        BookMetaListResponse response = new BookMetaListResponse();
        response.totalPage = 0;
        externalBookMetaList.forEach(bookMeta ->
                response.bookMetaList.add(BookMetaResponse.from(bookMeta))
        );

        return response;
    }

    public static BookMetaListResponse from(Page<BookMeta> bookMetaPage) {
        BookMetaListResponse response = new BookMetaListResponse();
        response.totalPage = bookMetaPage.getTotalPages();
        bookMetaPage
                .getContent()
                .forEach(bookMeta ->
                        response.bookMetaList.add(BookMetaResponse.from(bookMeta))
                );

        return response;
    }
}
