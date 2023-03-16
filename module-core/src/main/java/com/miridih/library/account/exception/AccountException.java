package com.miridih.library.account.exception;

public class AccountException extends RuntimeException {

    private final transient Object data;

    public AccountException(String message, Object data) {
        super(message);
        this.data = data;
    }
    public Object getData() {
        return data;
    }
}
