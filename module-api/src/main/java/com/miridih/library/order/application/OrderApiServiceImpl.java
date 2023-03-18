package com.miridih.library.order.application;

import com.miridih.library.account.application.AccountService;
import com.miridih.library.account.domain.Account;
import com.miridih.library.order.application.dto.OrderCreateInput;
import com.miridih.library.order.application.dto.OrderSearchCondition;
import com.miridih.library.order.application.dto.OrderUpdateInput;
import com.miridih.library.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderApiServiceImpl implements OrderApiService {

    private final OrderService orderService;
    private final AccountService accountService;

    @Override
    public Order findOrder(String email, Long orderId) {
        Order order = orderService.getById(orderId);
        Account account = accountService.getByEmail(email);

        validateOrderAccount(order, account);

        return order;
    }

    @Override
    public Page<Order> findAllOrders(String email, Pageable pageable) {
        Account account = accountService.getByEmail(email);
        OrderSearchCondition searchCondition =
                OrderSearchCondition
                        .builder()
                        .accountId(account.getId())
                        .pageable(pageable)
                        .build();

        return orderService.getAll(searchCondition);
    }

    @Override
    public Order orderBook(String email, OrderCreateInput input) {
        Account account = accountService.getByEmail(email);
        Order order = Order.create(input.getTitle(), input.getQuantity(), input.getLink(), input.getNote(), account);

        return orderService.save(order);
    }

    @Override
    public Order updateOrder(String email, OrderUpdateInput input) {
        Order order = orderService.getById(input.getOrderId());
        Account account = accountService.getByEmail(email);

        validateOrderAccount(order, account);
        if(order.isBeingProcessed()) {
            throw new RuntimeException("주문을 업데이트 할 수 없는 상태입니다.");
        }

        String note = Optional.ofNullable(input.getNote()).orElse(order.getNote());
        order.updateNote(note);
        order.updateQuantity(input.getQuantity());

        return orderService.update(order);
    }

    @Override
    public void deleteOrder(String email, Long orderId) {
        Account account = accountService.getByEmail(email);
        Order order = orderService.getById(orderId);

        validateOrderAccount(order, account);
        if(order.isBeingProcessed()) {
            throw new RuntimeException("주문이 진행중이여서 삭제할 수 없습니다.");
        }

        order.delete();
        orderService.update(order);
    }

    private void validateOrderAccount(Order order, Account account) {
        if(!order.getAccount().isSameAccount(account)) {
            throw new RuntimeException("사용자 정보가 다릅니다.");
        }
    }
}
