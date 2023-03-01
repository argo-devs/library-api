package com.miridih.library.order.ui;

import com.miridih.library.order.application.dto.OrderUpdateInput;
import com.miridih.library.order.domain.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderUpdateRequest {
    private Long orderId;
    private OrderStatus status;

    public OrderUpdateInput toOrderUpdateInput() {
        return OrderUpdateInput
                .builder()
                .orderId(orderId)
                .status(status)
                .build();
    }
}
