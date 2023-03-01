package com.miridih.library.account.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private final Account account;
//    private final List<Authority> authorities;
    private final List<GrantedAuthority> authorityList = new ArrayList<>();

    public User(Account account) {
        this.account = account;
        this.authorityList.add(new SimpleGrantedAuthority(account.getRole().name()));
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public boolean isEnabled() {
        return account.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }


    // ========================================================
    // Spring Security 를 사용하기 위해 기본으로 true 를 반환한다.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
