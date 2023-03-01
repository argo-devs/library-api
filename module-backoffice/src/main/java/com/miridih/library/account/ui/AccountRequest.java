package com.miridih.library.account.ui;

import com.miridih.library.account.application.dto.AccountInput;
import com.miridih.library.account.domain.enums.AccountRole;
import lombok.Data;
import lombok.NonNull;

@Data
public class AccountRequest {
    @Data
    public static class Register {
        private String name;
        private String email;
        private String password;
        private Role role;

        public AccountInput toAccountInput() {
            return AccountInput
                    .builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .role(AccountRole.valueOf(role.toAccountRole()))
                    .build();
        }
    }

    @Data
    public static class Update {
        @NonNull
        private Long accountId;
        private String name;
        private String email;
        private String password;
        private Role role;

        public AccountInput toAccountInput() {
            return AccountInput
                    .builder()
                    .accountId(accountId)
                    .name(name)
                    .email(email)
                    .password(password)
                    .role(AccountRole.valueOf(role.toAccountRole()))
                    .build();
        }
    }
}
