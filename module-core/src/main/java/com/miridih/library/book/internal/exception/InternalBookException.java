package com.miridih.library.book.internal.exception;

public class InternalBookException extends RuntimeException {

    private final transient Object data;

    public InternalBookException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;

    }
}
