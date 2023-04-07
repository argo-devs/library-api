package com.miridih.library.auth.ui.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
public class TokenAccessRequest {
    @Schema(description = "이메일", example = "test@miridih.com")
    @NotBlank
    private String email;

    @Schema(description = "비밀번호", example = "helloworld")
    @ToString.Exclude
    @NotBlank
    private String password;
}
