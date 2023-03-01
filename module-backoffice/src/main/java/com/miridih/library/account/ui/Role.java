package com.miridih.library.account.ui;

import com.miridih.library.account.domain.enums.AccountRole;

enum Role {
    USER,
    ADMIN
    ;

    private static final String ROLE_PREFIX = "ROLE_";

    String toAccountRole() {
        return ROLE_PREFIX + this.name();
    }

    static String from(AccountRole role) {
        return role.name().substring(ROLE_PREFIX.length());
    }
}
