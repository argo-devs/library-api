package com.miridih.library.auth.ui.request;

import lombok.Data;

@Data
public class TokenAccessRequest {
    private String email;
    private String password;
}
