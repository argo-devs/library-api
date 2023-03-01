package com.miridih.library.account.application.dto;

import com.miridih.library.account.domain.enums.AccountRole;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AccountInput {
    private Long accountId;
    private String email;
    private String name;
    private String password;
    private AccountRole role;
}
