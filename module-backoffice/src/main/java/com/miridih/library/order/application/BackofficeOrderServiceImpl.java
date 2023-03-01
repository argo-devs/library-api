package com.miridih.library.order.application;

import com.miridih.library.order.application.dto.OrderSearchCondition;
import com.miridih.library.order.application.dto.OrderUpdateInput;
import com.miridih.library.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BackofficeOrderServiceImpl implements BackofficeOrderService {

    private final OrderService orderService;

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        OrderSearchCondition searchCondition =
                OrderSearchCondition
                        .builder()
                        .pageable(pageable)
                        .build();

        return orderService.getAll(searchCondition);
    }

    @Override
    public Order updateOrder(OrderUpdateInput input) {
        Order order = orderService.get(input.getOrderId());
        order.updateStatus(input.getStatus());

        return orderService.update(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderService.delete(orderId);
    }
}
