package com.miridih.library.book.ui.request;

import com.miridih.library.book.application.dto.BookMetaSearchCondition;
import com.miridih.library.book.application.dto.ExternalBookMetaSearchCondition;
import lombok.Data;
import lombok.Getter;

@Data
public class BookMetaSearchRequest {
    @Getter
    private enum Sort {
        REGISTERED_DATE,
        NAME
    }

    private Long bookMetaId;
    private Long categoryId;
    private String name;
    private int page = 0;
    private int size = 10;
    private Sort sort = Sort.REGISTERED_DATE;

    public ExternalBookMetaSearchCondition toExternalBookMetaSearchCondition() {
        return ExternalBookMetaSearchCondition
                .builder()
                .query(name)
                .page(page)
                .size(size)
                .build();
    }

    public BookMetaSearchCondition toBookMetaSearchCondition() {
        return BookMetaSearchCondition
                .builder()
                .bookMetaId(bookMetaId)
                .name(name)
                .categoryId(categoryId)
                .page(page)
                .size(size)
                .sort(BookMetaSearchCondition.Sort.valueOf(sort.name()))
                .build();
    }
}
