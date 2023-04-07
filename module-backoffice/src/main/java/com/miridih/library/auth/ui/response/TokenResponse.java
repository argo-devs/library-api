package com.miridih.library.auth.ui.response;

import com.miridih.library.auth.domain.JwtToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {
    @Schema(description = "권한의 위임 방법", example = "bearer")
    private String grantType;

    @Schema(description = "엑세스 토큰", example = "981u3iu2gwdga8s7gciaubc")
    private String accessToken;

    @Schema(description = "리프레시 토큰", example = "laskd12e9j1wkdnih8i")
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
