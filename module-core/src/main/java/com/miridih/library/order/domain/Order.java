package com.miridih.library.order.domain;

import com.miridih.library.account.domain.Account;
import com.miridih.library.order.domain.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String title;
    private int quantity;
    private String link;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime requestedDate;
    private LocalDateTime updatedDate;

    public static Order create(String title, int quantity, String link) {
        Order order = new Order();
        order.title = title;
        order.quantity = quantity;
        order.link = link;
        order.status = OrderStatus.SUBMIT;
        order.requestedDate = LocalDateTime.now();
        order.updatedDate = order.requestedDate;

        return order;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public void delete() {
        status = OrderStatus.INACTIVE;
    }
}
