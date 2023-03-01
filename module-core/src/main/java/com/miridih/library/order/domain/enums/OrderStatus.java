package com.miridih.library.order.domain.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    SUBMIT("요청중"),
    IN_PROGRESS("처리중"),
    COMPLETE("완료"),
    INACTIVE("비활성화");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }
}
