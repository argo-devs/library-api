package com.miridih.library.auth.exception;

public class TokenException extends AuthException {

    public TokenException(String message, Object data) {
        super(message, data);
    }

    public TokenException(String message, Throwable cause, Object data) {
        super(message, cause, data);
    }
}
