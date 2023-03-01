package com.miridih.library.auth.application;

import com.miridih.library.auth.domain.JwtToken;

public interface BackofficeAuthService {
    JwtToken issueToken(String email, String password);
    JwtToken reIssueToken(String accessToken, String refreshToken);
    void invalidateToken(String email);
}
