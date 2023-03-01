package com.miridih.library.order.ui;

import com.miridih.library.order.domain.Order;
import lombok.Data;

@Data
public class OrderResponse {
    private Long orderId;
    private String name;
    private String email;
    private String title;
    private int quantity;
    private String link;
    private String status;
    private String requestedDate;

    public static OrderResponse from(Order order) {
        OrderResponse response = new OrderResponse();
        response.orderId = order.getId();
        response.name = order.getAccount().getName();
        response.email = order.getAccount().getEmail();
        response.title = order.getTitle();
        response.quantity = order.getQuantity();
        response.link = order.getLink();
        response.status = order.getStatus().getDisplayName();
        response.requestedDate = order.getRequestedDate().toString();

        return response;
    }
}
