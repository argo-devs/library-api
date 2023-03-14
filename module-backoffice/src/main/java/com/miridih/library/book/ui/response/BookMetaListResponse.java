package com.miridih.library.book.ui.response;

import com.miridih.library.book.internal.domain.BookMeta;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class BookMetaListResponse {
    private int totalPage;
    private final List<BookMetaResponse> bookMetaList = new ArrayList<>();

    public static BookMetaListResponse from(Page<BookMeta> bookMetaPage) {
        BookMetaListResponse response = new BookMetaListResponse();
        response.totalPage = bookMetaPage.getTotalPages();
        bookMetaPage
                .getContent()
                .forEach(bookMeta -> response.bookMetaList.add(BookMetaResponse.from(bookMeta)));

        return response;
    }
}
