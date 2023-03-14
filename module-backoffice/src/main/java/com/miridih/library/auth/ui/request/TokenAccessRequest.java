package com.miridih.library.auth.ui.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TokenAccessRequest {
    private String email;
    @ToString.Exclude
    private String password;
}
