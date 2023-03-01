package com.miridih.library.auth.application;

import com.miridih.library.auth.application.dto.TokenInfo;
import com.miridih.library.auth.application.dto.UserCredential;
import com.miridih.library.auth.domain.JwtToken;
import com.miridih.library.auth.domain.RefreshToken;
import com.miridih.library.auth.infrastructure.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private static final String AUTH_SCHEME = "Bearer";

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public JwtToken issueAccessToken(UserCredential user) {
        String email = user.getEmail();
        String password = user.getPassword();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if(!authentication.isAuthenticated()) {
            throw new RuntimeException("인증되지 않았습니다.");
        }

        // jwt 토큰 생성
        final JwtToken jwtToken = jwtTokenProvider.createJwtToken(authentication);

        // refresh 토큰 저장
        refreshTokenRepository.save(RefreshToken.of(email, jwtToken.getRefreshToken()));

        return jwtToken;
    }

    @Override
    public JwtToken reIssueAccessToken(TokenInfo tokenInfo) {
        Authentication authentication = jwtTokenProvider.getAuthentication(tokenInfo.getAccessToken());
        String email = authentication.getName();

        RefreshToken refreshToken = refreshTokenRepository
                .findById(email)
                .orElseThrow(() -> new RuntimeException("토큰을 찾을 수 없습니다."));

        if(!jwtTokenProvider.isValidToken(refreshToken.getValue())) {
            throw new RuntimeException("토큰이 유효하지 않습니다."); // todo: isValidToken 에서 throw 하게 변경
        }

        String newAccessToken = jwtTokenProvider.createAccessToken(authentication);

        return JwtToken
                .builder()
                .grantType(AUTH_SCHEME)
                .accessToken(newAccessToken)
                .refreshToken(refreshToken.getValue())
                .build();
    }

    @Override
    public void deleteRefreshToken(String key) {
        refreshTokenRepository.deleteById(key);
    }
}
