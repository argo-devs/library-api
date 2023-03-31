package com.miridih.library.book.exception;

public class BookMetaException extends InternalBookException {

    public BookMetaException(String message, Object data) {
        super(message, data);
    }

    public BookMetaException(String message, Throwable cause, Object data) {
        super(message, cause, data);
    }
}
