package com.miridih.library.book.exception;

public class InternalBookException extends RuntimeException {

    private final transient Object data;

    public InternalBookException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public InternalBookException(String message, Throwable cause, Object data) {
        super(message, cause);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
