package com.miridih.library.auth.ui.response;

import com.miridih.library.auth.domain.JwtToken;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {
    private String grantType;
    private String accessToken;
    private String refreshToken;

    public static TokenResponse from(JwtToken token) {
        return TokenResponse
                .builder()
                .grantType(token.getGrantType())
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
