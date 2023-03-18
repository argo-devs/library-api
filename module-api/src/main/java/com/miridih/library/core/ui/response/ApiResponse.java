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
public class ApiResponse<T> {
    private final ZonedDateTime timestamp = ZonedDateTime.now();
    private T data;
    private ErrorStatus errorCode;
    private String errorMessage;

    public static <T> ApiResponse<T> of(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.data = data;

        return response;
    }

    public static <T> ApiResponse<T> of(ErrorStatus errorCode, String errorMessage) {
        ApiResponse<T> response = new ApiResponse<>();
        response.errorCode = errorCode;
        response.errorMessage = errorMessage;

        return response;
    }
}
