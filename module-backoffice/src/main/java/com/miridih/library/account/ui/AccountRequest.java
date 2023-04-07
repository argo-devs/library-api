package com.miridih.library.account.ui;

import com.miridih.library.account.application.dto.AccountInput;
import com.miridih.library.account.domain.enums.AccountRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

public class AccountRequest {

    @Data
    public static class CreateRequest {
        @Schema(description = "사용자 이름", example = "아고")
        @NotBlank
        private String name;

        @Schema(description = "이메일", example = "argo@miridih.com")
        @NotBlank
        private String email;

        @Schema(description = "비밀번호", example = "helloworld")
        @NotBlank
        private String password;

        @Schema(description = "이메일 수신여부", example = "true", defaultValue = "false")
        private boolean receiveEmail;

        @Schema(description = "사용자 역할", example = "USER", defaultValue = "USER")
        private Role role = Role.USER;

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

    @Data
    public static class UpdateRequest {
        @Schema(description = "사용자 ID", example = "1")
        @NotBlank
        private Long accountId;

        @Schema(description = "사용자 이름", example = "미리")
        private String name;

        @Schema(description = "비밀번호", example = "hello")
        private String password;

        @Schema(description = "메일 수신여부", example = "true")
        private Boolean receiveEmail;

        @Schema(description = "사용자 역할", example = "ADMIN")
        private Role role;

        public AccountInput toAccountInput() {
            return AccountInput
                    .builder()
                    .accountId(accountId)
                    .name(name)
                    .password(password)
                    .receiveEmail(receiveEmail)
                    .role(AccountRole.valueOf(role.toAccountRole()))
                    .build();
        }
    }
}
