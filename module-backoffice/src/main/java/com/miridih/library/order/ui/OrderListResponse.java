package com.miridih.library.order.ui;

import com.miridih.library.order.domain.Order;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderListResponse {
    private int totalPage;
    private final List<OrderResponse> orderList = new ArrayList<>();

    public static OrderListResponse from(Page<Order> orders) {
        OrderListResponse response = new OrderListResponse();
        response.totalPage = orders.getTotalPages();
        orders.getContent()
                .forEach(order ->
                        response.orderList.add(OrderResponse.from(order))
                );

        return response;
    }
}
