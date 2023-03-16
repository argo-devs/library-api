package com.miridih.library.account.ui;

import com.miridih.library.account.application.dto.AccountInput;
import com.miridih.library.account.domain.enums.AccountRole;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

public class AccountRequest {
    @Getter
    @ToString
    public static class Register {
        private String name;
        private String email;
        private String password;
        private Boolean receiveEmail;
        private Role role;

        public AccountInput toAccountInput() {
            return AccountInput
                    .builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .receiveEmail(receiveEmail)
                    .role(AccountRole.valueOf(role.toAccountRole()))
                    .build();
        }
    }

    @Getter
    @ToString
    public static class Update {
        @NonNull
        private Long accountId;
        private String name;
        private String email;
        private String password;
        private Boolean receiveEmail;
        private Role role;

        public AccountInput toAccountInput() {
            return AccountInput
                    .builder()
                    .accountId(accountId)
                    .name(name)
                    .email(email)
                    .password(password)
                    .receiveEmail(receiveEmail)
                    .role(AccountRole.valueOf(role.toAccountRole()))
                    .build();
        }
    }
}
