package com.miridih.library.account.application;

import com.miridih.library.account.application.dto.AccountInput;
import com.miridih.library.account.domain.Account;
import com.miridih.library.account.domain.enums.AccountRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackofficeAccountServiceImpl implements BackofficeAccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    @Override
    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountService.getAll(pageable);
    }

    @Override
    public Account getAccount(Long accountId) {
        return accountService.getById(accountId);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountService.getByEmail(email);
    }

    @Override
    @Transactional
    public Account registerAccount(AccountInput accountInput) {
        Account account = Account.createNewAccount(
                accountInput.getName(),
                accountInput.getEmail(),
                passwordEncoder.encode(accountInput.getPassword()),
                accountInput.getReceiveEmail(),
                accountInput.getRole()
        );

        return accountService.save(account);
    }

    @Override
    @Transactional
    public Account updateAccount(AccountInput accountInput) {
        Account account = accountService.getById(accountInput.getAccountId());

        String name = Optional.ofNullable(accountInput.getName()).orElse(account.getName());
        String email = Optional.ofNullable(accountInput.getName()).orElse(account.getEmail());
        String password = Optional
                .ofNullable(accountInput.getPassword())
                .map(passwordEncoder::encode)
                .orElse(account.getPassword());

        boolean receiveEmail = Optional.ofNullable(accountInput.getReceiveEmail()).orElse(account.isReceiveEmail());
        AccountRole role = Optional.ofNullable(accountInput.getRole()).orElse(account.getRole());

        account.updateDetails(name, email, password, receiveEmail, role);

        return accountService.update(account);
    }

    @Override
    public void activateAccount(Long accountId) {
        Account account = accountService.getById(accountId);
        account.activate(); // 활성화

        accountService.update(account);
    }

    @Override
    public void deactivateAccount(Long accountId) {
        Account account = accountService.getById(accountId);
        account.deactivate(); // 비활성화

        accountService.update(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Long accountId) {
        accountService.delete(accountId);
    }
}
