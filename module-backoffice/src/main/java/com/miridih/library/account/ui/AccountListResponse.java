package com.miridih.library.account.ui;

import com.miridih.library.account.domain.Account;
import com.miridih.library.core.ui.response.PaginationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class AccountListResponse {
    @Schema(description = "사용자 리스트")
    private final List<AccountResponse> accountList = new ArrayList<>();

    @NotBlank
    private PaginationResponse pagination;

    public static AccountListResponse from(Page<Account> accountPage) {
        AccountListResponse response = new AccountListResponse();
        accountPage
                .getContent()
                .forEach(account ->
                        response.accountList.add(AccountResponse.from(account))
                );
        response.pagination = PaginationResponse.from(accountPage);

        return response;
    }
}
