package com.miridih.library.account.application;

import com.miridih.library.account.domain.Account;
import com.miridih.library.account.exception.AccountExistException;
import com.miridih.library.account.exception.AccountNotFoundException;
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
        accountRepository
                .findByEmail(account.getEmail())
                .ifPresent(acc-> {
                    throw new AccountExistException("이미 존재하는 계정입니다.", account.getEmail());
                });

        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        getById(account.getId()); // 계정이 존재하는지 확인
        account.updated();

        return accountRepository.save(account);
    }

    @Override
    public void delete(Long accountId) {
        getById(accountId); // 계정이 존재하는지 확인

        accountRepository.deleteById(accountId);
    }

    private Account getAccountIfExist(Optional<Account> account) {
        return account
                .orElseThrow(() ->
                        new AccountNotFoundException("사용자를 찾을 수 없습니다.", account)
                );
    }
}
