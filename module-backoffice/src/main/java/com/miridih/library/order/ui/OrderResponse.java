package com.miridih.library.order.ui;

import com.miridih.library.order.domain.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class OrderResponse {
    @Schema(description = "주문 ID", example = "1")
    @NotBlank
    private Long orderId;

    @Schema(description = "주문자 이름", example = "아고")
    @NotBlank
    private String name;

    @Schema(description = "주문자 이메일", example = "argo@miridih.com")
    @NotBlank
    private String email;

    @Schema(description = "도서 제목", example = "알고리즘 101")
    @NotBlank
    private String title;

    @Schema(description = "도서 신청수", example = "3")
    @NotBlank
    private int quantity;

    @Schema(description = "도서 구매 링크", example = "https://image.com")
    @NotBlank
    private String link;

    @Schema(description = "주문 상태", example = "요청중")
    @NotBlank
    private String status;

    @Schema(description = "도서 신청일", example = "2023-04-06T20:50:18.893457")
    @NotBlank
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

    public static OrderResponse of(Long orderId) {
        OrderResponse response = new OrderResponse();
        response.orderId = orderId;

        return response;
    }
}
