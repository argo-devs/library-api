package com.miridih.library.account.ui;

import com.miridih.library.account.application.BackofficeAccountService;
import com.miridih.library.account.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeAccountController {

    private final BackofficeAccountService backofficeAccountService;

    @GetMapping("/account/{accountId}")
    public AccountResponse getAccount(@PathVariable Long accountId) {
        Account account = backofficeAccountService.getAccount(accountId);

        return AccountResponse.from(account);
    }

    @GetMapping("/account")
    public AccountListResponse getAllAccounts(Pageable pageable) {
        Page<Account> accountList = backofficeAccountService.getAllAccounts(pageable);

        return AccountListResponse.from(accountList);
    }

    @PostMapping(value = "/account")
    public AccountResponse registerAccount(@RequestBody AccountRequest.Register request) {
        Account account = backofficeAccountService.registerAccount(request.toAccountInput());

        return AccountResponse.from(account);
    }

    @PutMapping("/account")
    public AccountResponse updateAccount(@RequestBody AccountRequest.Update request) {
        Account account = backofficeAccountService.updateAccount(request.toAccountInput());

        return AccountResponse.from(account);
    }

    @DeleteMapping("/account/{accountId}")
    public void deleteAccount(@PathVariable Long accountId) {
        backofficeAccountService.deleteAccount(accountId);
    }

    @PostMapping("/account/status/{accountId}")
    public void activate(@PathVariable Long accountId) {
        backofficeAccountService.activateAccount(accountId);
    }

    @DeleteMapping("/account/status/{accountId}")
    public void deactivate(@PathVariable Long accountId) {
        backofficeAccountService.deactivateAccount(accountId);
    }
}
