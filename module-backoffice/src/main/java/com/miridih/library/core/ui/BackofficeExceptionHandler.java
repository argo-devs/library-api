package com.miridih.library.core.ui;

import com.miridih.library.auth.exception.AuthException;
import com.miridih.library.core.ui.response.BackofficeResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BackofficeExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BackofficeResponse<?>> exceptionHandler(Exception e) {
        return new ResponseEntity<>(
                BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<BackofficeResponse<?>> AuthExceptionHandler(AuthException e) {
        return new ResponseEntity<>(
                BackofficeResponse.of(ErrorStatus.E1, e.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

}
