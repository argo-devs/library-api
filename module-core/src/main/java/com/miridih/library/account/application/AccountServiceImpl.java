package com.miridih.library.account.application;

import com.miridih.library.account.domain.Account;
import com.miridih.library.account.infrastructure.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Page<Account> getAll(Pageable pageable) {
        return accountRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public Account getById(Long accountId) {
        Optional<Account> account =  accountRepository.findById(accountId);

        return getAccountIfExist(account);
    }

    @Override
    public Account getByEmail(String email) {
        Optional<Account> account =  accountRepository.findByEmail(email);

        return getAccountIfExist(account);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        account.updated();

        return save(account);
    }

    @Override
    public void delete(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    private Account getAccountIfExist(Optional<Account> account) {
        return account.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }
}
