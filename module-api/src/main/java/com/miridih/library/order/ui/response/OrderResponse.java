package com.miridih.library.order.ui.response;

import com.miridih.library.order.domain.Order;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderResponse {
    private Long orderId;
    private String title;
    private int quantity;
    private String link;
    private String status;
    private LocalDateTime requestedDate;
    private LocalDateTime updatedDate;

    public static OrderResponse from(Order order) {
        return OrderResponse
                .builder()
                .orderId(order.getId())
                .title(order.getTitle())
                .quantity(order.getQuantity())
                .link(order.getLink())
                .status(order.getStatus().getDisplayName())
                .requestedDate(order.getRequestedDate())
                .updatedDate(order.getUpdatedDate())
                .build();
    }

    public static OrderResponse of(Long orderId) {
        return OrderResponse
                .builder()
                .orderId(orderId)
                .build();
    }
}
