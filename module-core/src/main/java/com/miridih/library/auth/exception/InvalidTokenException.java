package com.miridih.library.auth.exception;

public class InvalidTokenException extends TokenException {

    public InvalidTokenException(String message, Object data) {
        super(message, data);
    }

    public InvalidTokenException(String message, Throwable cause, Object data) {
        super(message, cause, data);
    }
}
