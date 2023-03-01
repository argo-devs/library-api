package com.miridih.library.auth.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UserCredential {
    private String email;
    @ToString.Exclude
    private String password;
}
