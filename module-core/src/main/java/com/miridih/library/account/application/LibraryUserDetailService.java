package com.miridih.library.account.application;

import com.miridih.library.account.domain.Account;
import com.miridih.library.account.domain.User;
import com.miridih.library.account.infrastructure.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Account account = accountRepository
                .findByEmail(email)
                .orElse(null);

        if(account != null && account.isEnabled()) {
            return new User(account);
        }

        return null;
    }
}
