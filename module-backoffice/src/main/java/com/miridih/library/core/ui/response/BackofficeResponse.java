package com.miridih.library.core.ui.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BackofficeResponse<T> {
    private final ZonedDateTime timestamp = ZonedDateTime.now();
    private T data;
    private ErrorStatus errorCode;
    private String errorMessage;

    public static <T> BackofficeResponse<T> of(T data) {
        BackofficeResponse<T> response = new BackofficeResponse<>();
        response.data = data;

        return response;
    }

    public static <T> BackofficeResponse<T> of(ErrorStatus errorCode, String errorMessage) {
        BackofficeResponse<T> response = new BackofficeResponse<>();
        response.errorCode = errorCode;
        response.errorMessage = errorMessage;

        return response;
    }
}
