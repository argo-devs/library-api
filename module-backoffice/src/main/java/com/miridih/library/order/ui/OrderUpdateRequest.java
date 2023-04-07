package com.miridih.library.order.ui;

import com.miridih.library.order.application.dto.OrderUpdateInput;
import com.miridih.library.order.domain.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderUpdateRequest {
    @Schema(description = "주문 ID", example = "1")
    @NotBlank
    private Long orderId;

    @Schema(description = "주문 상태", example = "COMPLETE")
    @NotBlank
    private OrderStatus status;

    public OrderUpdateInput toOrderUpdateInput() {
        return OrderUpdateInput
                .builder()
                .orderId(orderId)
                .status(status)
                .build();
    }
}
