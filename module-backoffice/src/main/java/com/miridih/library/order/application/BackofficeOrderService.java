package com.miridih.library.order.application;

import com.miridih.library.order.application.dto.OrderUpdateInput;
import com.miridih.library.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BackofficeOrderService {
    Page<Order> getOrders(Pageable pageable);
    Order updateOrder(OrderUpdateInput input);
    void deleteOrder(Long orderId);
}
