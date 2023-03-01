package com.miridih.library.auth.ui.request;

import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String accessToken;
    private String refreshToken;
}
