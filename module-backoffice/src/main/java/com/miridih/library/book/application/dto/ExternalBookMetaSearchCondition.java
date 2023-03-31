package com.miridih.library.book.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
@ToString
public class ExternalBookMetaSearchCondition {
    private String query;
    private int page;
    private int size;

    public Pageable getPageable() {
        return PageRequest.of(getPage(), getSize());
    }
}
