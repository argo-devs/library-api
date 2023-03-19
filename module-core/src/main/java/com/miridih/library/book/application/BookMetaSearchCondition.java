package com.miridih.library.book.application;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
@ToString
public class BookMetaSearchCondition {
    private Long bookMetaId;
    private String name;
    private Pageable pageable;
}
