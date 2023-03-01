package com.miridih.library.auth.application;

import com.miridih.library.auth.application.dto.TokenInfo;
import com.miridih.library.auth.application.dto.UserCredential;
import com.miridih.library.auth.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BackofficeAuthServiceImpl implements BackofficeAuthService {

    private final JwtTokenService jwtTokenService;
    @Override
    public JwtToken issueToken(String email, String password) {
        UserCredential user = new UserCredential(email, password);

        return jwtTokenService.issueAccessToken(user);
    }

    @Override
    public JwtToken reIssueToken(String accessToken, String refreshToken) {
        return jwtTokenService.reIssueAccessToken(new TokenInfo(accessToken, refreshToken));
    }

    @Override
    public void invalidateToken(String email) {
        jwtTokenService.deleteRefreshToken(email);
    }
}
