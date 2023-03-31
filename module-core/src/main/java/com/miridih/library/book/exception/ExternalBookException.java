package com.miridih.library.book.exception;

public class ExternalBookException extends RuntimeException {

    private final transient Object data;

    public ExternalBookException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public ExternalBookException(String message, Throwable cause, Object data) {
        super(message, cause);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
