package com.miridih.library.order.application;

import com.miridih.library.order.application.dto.OrderCreateInput;
import com.miridih.library.order.application.dto.OrderUpdateInput;
import com.miridih.library.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderApiService {
    Order findOrder(String email, Long orderId);
    Page<Order> findAllOrders(String email, Pageable pageable);
    Order orderBook(String email, OrderCreateInput input);
    Order updateOrder(String email, OrderUpdateInput input);
    void deleteOrder(String email, Long orderId);
}
