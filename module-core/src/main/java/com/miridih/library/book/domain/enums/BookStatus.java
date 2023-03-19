package com.miridih.library.book.domain.enums;

import lombok.Getter;

@Getter
public enum BookStatus {
    ACTIVE("대출 가능"),
    INACTIVE("비활성"),
    IN_LOAN("대출 불가"),
    ;

    private final String value;

    BookStatus(String value) {
        this.value = value;
    }
}
