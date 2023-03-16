package com.miridih.library.order.exception;

public class OrderNotFoundException extends OrderException {

    public OrderNotFoundException(String message, Object data) {
        super(message, data);
    }
}
