package com.miridih.library.account.domain;

import com.miridih.library.account.domain.enums.AccountRole;
import com.miridih.library.order.domain.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @ToString.Exclude
    private String password;
    @Column(name = "enabled")
    private boolean isEnabled;
    @Enumerated(EnumType.STRING)
    private AccountRole role;
    private LocalDateTime registeredDate;
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "account")
    private List<Order> orderList = new ArrayList<>();

    public static Account withId(Long accountId) {
        Account account = new Account();
        account.id = accountId;

        return account;
    }
    public static Account createNewAccount(String name, String email, String password, AccountRole role) {
        Account account = new Account();
        account.name = name;
        account.email = email;
        account.password = password;
        account.role = role;
        account.isEnabled = true;
        account.registeredDate = LocalDateTime.now();
        account.updatedDate = account.registeredDate;

        return account;
    }

    public void updateDetails(String name, String email, String password, AccountRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void activate() {
        isEnabled = true;
    }
    public void deactivate() {
        isEnabled = false;
    }

    public void updated() {
        this.updatedDate = LocalDateTime.now();
    }
}
