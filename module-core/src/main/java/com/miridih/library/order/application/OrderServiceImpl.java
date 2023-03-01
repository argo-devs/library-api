package com.miridih.library.order.application;

import com.miridih.library.account.domain.Account;
import com.miridih.library.order.application.dto.OrderSearchCondition;
import com.miridih.library.order.domain.Order;
import com.miridih.library.order.infrastructure.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order get(Long orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
    }

    @Override
    public Page<Order> getAll(OrderSearchCondition condition) {
        if(condition.getAccountId() != null) {
            return orderRepository
                    .findAllByAccountOrderByIdDesc(Account.withId(condition.getAccountId()), condition.getPageable());
        }

        return orderRepository.findAllByOrderByIdDesc(condition.getPageable());
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return save(order);
    }

    @Override
    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
