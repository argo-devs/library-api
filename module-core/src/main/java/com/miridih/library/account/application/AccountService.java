package com.miridih.library.account.application;

import com.miridih.library.account.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    Page<Account> getAll(Pageable pageable);
    Account getById(Long accountId);
    Account getByEmail(String email);
    Account save(Account account);
    Account update(Account account);
    void delete(Long accountId);
}
