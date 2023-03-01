package com.miridih.library.order.infrastructure;

import com.miridih.library.account.domain.Account;
import com.miridih.library.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByOrderByIdDesc(Pageable pageable);

    Page<Order> findAllByAccountOrderByIdDesc(Account account, Pageable pageable);
}
