package com.miridih.library.order.application.dto;

import com.miridih.library.order.domain.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class OrderUpdateInput {
    private Long orderId;
    private OrderStatus status;
}
