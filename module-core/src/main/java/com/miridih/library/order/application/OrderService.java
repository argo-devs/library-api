package com.miridih.library.order.application;

import com.miridih.library.order.application.dto.OrderSearchCondition;
import com.miridih.library.order.domain.Order;
import org.springframework.data.domain.Page;

public interface OrderService {
    Order getById(Long orderId);
    Page<Order> getAll(OrderSearchCondition condition);
    Order save(Order order);
    Order update(Order order);
    void delete(Long orderId);
}
