package com.miridih.library.auth.exception;

public class RefreshTokenNotFoundException extends TokenException {

    public RefreshTokenNotFoundException(String message, Object data) {
        super(message, data);
    }
}
