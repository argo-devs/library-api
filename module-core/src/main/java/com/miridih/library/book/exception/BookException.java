package com.miridih.library.book.exception;

public class BookException extends InternalBookException {

    public BookException(String message) {
        super(message, null);
    }

    public BookException(String message, Object data) {
        super(message, data);
    }
}
