package com.miridih.library.account.exception;

public class AccountNotFoundException extends AccountException {

    public AccountNotFoundException(String message, Object data) {
        super(message, data);
    }
}
