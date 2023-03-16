package com.miridih.library.order.exception;

public class OrderException extends RuntimeException {

    private final transient Object data;

    public OrderException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
