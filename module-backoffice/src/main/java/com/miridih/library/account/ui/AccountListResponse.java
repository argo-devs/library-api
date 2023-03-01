package com.miridih.library.account.ui;

import com.miridih.library.account.domain.Account;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AccountListResponse {
    private int totalPage;
    private List<AccountResponse> accounts;

    public static AccountListResponse from(Page<Account> accounts) {
        AccountListResponse response = new AccountListResponse();
        response.totalPage = accounts.getTotalPages();
        response.accounts = accounts.getContent()
                .stream()
                .map(AccountResponse::from)
                .collect(Collectors.toList());

        return response;
    }
}
