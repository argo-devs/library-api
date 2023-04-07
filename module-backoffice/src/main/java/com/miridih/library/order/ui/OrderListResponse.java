package com.miridih.library.order.ui;

import com.miridih.library.core.ui.response.PaginationResponse;
import com.miridih.library.order.domain.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderListResponse {
    @Schema(description = "외부 도서 메타 리스트")
    @NotBlank
    private final List<OrderResponse> orderList = new ArrayList<>();

    @NotBlank
    private PaginationResponse pagination;


    public static OrderListResponse from(Page<Order> orderPage) {
        OrderListResponse response = new OrderListResponse();
        orderPage.getContent()
                .forEach(order ->
                        response.orderList.add(OrderResponse.from(order))
                );
        response.pagination = PaginationResponse.from(orderPage);

        return response;
    }
}
