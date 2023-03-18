package com.miridih.library.book.exception;

public class BookNotFoundException extends BookException {
    
    public BookNotFoundException(String message, Object data) {
        super(message, data);
    }
}
