package com.miridih.library.order.ui;

import com.miridih.library.order.domain.Order;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderListResponse {
    private int totalPage;
    private List<OrderResponse> orders = new ArrayList<>();

    public static OrderListResponse from(Page<Order> orders) {
        OrderListResponse response = new OrderListResponse();
        response.totalPage = orders.getTotalPages();
        response.orders = orders.getContent()
                .stream()
                .map(OrderResponse::from)
                .collect(Collectors.toList());

        return response;
    }
}
