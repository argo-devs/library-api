package com.miridih.library.core.ui.response;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    SERVER_ERROR("시스템 오류로 인해 요청을 처리할 수 없습니다. 관리자에게 문의 바랍니다.")
    ;

    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public static String of(String value) {
        return value;
    }
}
