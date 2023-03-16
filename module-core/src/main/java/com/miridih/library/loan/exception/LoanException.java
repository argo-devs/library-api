package com.miridih.library.loan.exception;

public class LoanException extends RuntimeException {

    private final transient Object data;

    public LoanException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
