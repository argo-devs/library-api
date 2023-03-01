package com.miridih.library.account.ui;

import com.miridih.library.account.domain.Account;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountResponse {
    private Long accountId;
    private String name;
    private String email;
    private String role;

    public static AccountResponse from(Account account) {
        return AccountResponse
                .builder()
                .accountId(account.getId())
                .name(account.getName())
                .email(account.getEmail())
                .role(Role.from(account.getRole()))
                .build();
    }
}
