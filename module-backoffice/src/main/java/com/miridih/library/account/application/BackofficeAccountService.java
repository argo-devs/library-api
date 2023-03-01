package com.miridih.library.account.application;

import com.miridih.library.account.application.dto.AccountInput;
import com.miridih.library.account.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BackofficeAccountService {
    Page<Account> getAllAccounts(Pageable pageable);
    Account getAccount(Long accountId);
    Account getAccountByEmail(String email);
    Account registerAccount(AccountInput accountInput);
    Account updateAccount(AccountInput accountInput);
    void activateAccount(Long accountId);
    void deactivateAccount(Long accountId);
    void deleteAccount(Long accountId);
}
