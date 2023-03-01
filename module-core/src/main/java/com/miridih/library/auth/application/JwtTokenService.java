package com.miridih.library.auth.application;

import com.miridih.library.auth.application.dto.TokenInfo;
import com.miridih.library.auth.application.dto.UserCredential;
import com.miridih.library.auth.domain.JwtToken;

public interface JwtTokenService {
    JwtToken issueAccessToken(UserCredential user);

    JwtToken reIssueAccessToken(TokenInfo tokenInfo);

    void deleteRefreshToken(String key);
}
