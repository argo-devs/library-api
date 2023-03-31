package com.miridih.library.book.application.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookMetaNameSearchCondition {
    private Long bookMetaId;
    private String name;
}
