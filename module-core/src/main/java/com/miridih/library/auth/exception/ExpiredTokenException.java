package com.miridih.library.auth.exception;

public class ExpiredTokenException extends TokenException {

    public ExpiredTokenException(String message, Object data) {
        super(message, data);
    }
}
