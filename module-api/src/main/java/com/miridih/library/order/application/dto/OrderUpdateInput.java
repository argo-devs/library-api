package com.miridih.library.order.application.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderUpdateInput {
    private Long orderId;
    private int quantity;
    private String note;
}
