package com.miridih.library.account.ui;

import com.miridih.library.account.domain.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class AccountResponse {
    @Schema(description = "사용자 ID", example = "2")
    @NotBlank
    private Long accountId;

    @Schema(description = "사용자 이름", example = "아고")
    @NotBlank
    private String name;

    @Schema(description = "이메일", example = "argo@miridih.com")
    @NotBlank
    private String email;

    @Schema(description = "사용자 역할", example = "USER")
    @NotBlank
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

    public static AccountResponse of(Long accountId) {
        return AccountResponse
                .builder()
                .accountId(accountId)
                .build();
    }
}
