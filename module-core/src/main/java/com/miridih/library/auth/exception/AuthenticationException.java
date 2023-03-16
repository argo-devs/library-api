package com.miridih.library.auth.exception;

public class AuthenticationException extends AuthException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Object data) {
        super(message, data);
    }
}
