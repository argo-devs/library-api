package com.miridih.library.order.ui;

import com.miridih.library.order.application.BackofficeOrderService;
import com.miridih.library.order.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeOrderController {

    private final BackofficeOrderService backofficeOrderService;

    @GetMapping("/order")
    public OrderListResponse getOrders(Pageable pageable) {
        Page<Order> orders = backofficeOrderService.getOrders(pageable);


        return OrderListResponse.from(orders);
    }

    @PutMapping("/order")
    public OrderResponse updateOrder(@RequestBody OrderUpdateRequest request) {
        Order order = backofficeOrderService.updateOrder(request.toOrderUpdateInput());

        return OrderResponse.from(order);
    }

    @DeleteMapping("/order/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        backofficeOrderService.deleteOrder(orderId);
    }
}
