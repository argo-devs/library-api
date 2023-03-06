package com.miridih.library.book.ui.request;

import com.miridih.library.book.application.dto.BookMetaSearchCondition;
import com.miridih.library.book.application.dto.ExternalBookMetaSearchCondition;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
public class BookMetaSearchRequest {
    private Long bookMetaId;
    private String name;
    private int page = 1;
    private int size = 15;

    public ExternalBookMetaSearchCondition toExternalBookMetaSearchCondition() {

        return ExternalBookMetaSearchCondition
                .builder()
                .query(name)
                .start(page)
                .display(size)
                .build();
    }

    public BookMetaSearchCondition toBookMetaSearchCondition() {
        Pageable pageable = PageRequest.of(page, size);

        return BookMetaSearchCondition
                .builder()
                .bookMetaId(bookMetaId)
                .name(name)
                .pageable(pageable)
                .build();
    }
}
