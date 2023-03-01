package com.miridih.library.auth.ui.response;

import lombok.Builder;

@Builder
public class TokenResponse {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
