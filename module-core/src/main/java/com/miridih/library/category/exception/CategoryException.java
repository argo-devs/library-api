package com.miridih.library.category.exception;

public class CategoryException extends RuntimeException {

    private final transient Object data;

    public CategoryException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
