package com.miridih.library.auth.exception;

public class AuthException extends RuntimeException {

    private final transient Object data;

    public AuthException(String message) {
        super(message);
        this.data = new Object();
    }

    public AuthException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public AuthException(String message, Throwable cause, Object data) {
        super(message, cause);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
