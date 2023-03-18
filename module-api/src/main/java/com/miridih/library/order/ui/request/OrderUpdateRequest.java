package com.miridih.library.order.ui.request;

import com.miridih.library.order.application.dto.OrderUpdateInput;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderUpdateRequest {
    private Long orderId;
    private int quantity;
    private String note;

    public OrderUpdateInput toOrderUpdateInput() {
        return OrderUpdateInput
                .builder()
                .orderId(orderId)
                .quantity(quantity)
                .note(note)
                .build();
    }
}
