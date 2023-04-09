package com.miridih.library.auth.ui;

import com.miridih.library.auth.application.BackofficeAuthService;
import com.miridih.library.auth.domain.JwtToken;
import com.miridih.library.auth.exception.AuthenticationException;
import com.miridih.library.auth.ui.request.TokenAccessRequest;
import com.miridih.library.auth.ui.request.TokenRefreshRequest;
import com.miridih.library.auth.ui.response.TokenResponse;
import com.miridih.library.core.ui.response.BackofficeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "사용자 인증 API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeAuthController {

    private final BackofficeAuthService backofficeAuthService;

    @Operation(summary = "로그인")
    @PostMapping("/auth")
    public BackofficeResponse<TokenResponse> accessToken(@RequestBody TokenAccessRequest request) {
        log.info("AUTH:TOKN:RQST: 토큰 발급 요청. [request={}]", request);
        JwtToken token = backofficeAuthService.issueToken(request.getEmail(), request.getPassword());

        return BackofficeResponse.of(TokenResponse.from(token));
    }

    @Operation(summary = "로그인 연장")
    @PutMapping("/auth")
    public BackofficeResponse<TokenResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        log.info("AUTH:RFSH:RQST: 토큰 재발급 요청. [request={}]", request);
        JwtToken token =  backofficeAuthService.reIssueToken(request.getAccessToken(), request.getRefreshToken());

        return BackofficeResponse.of(TokenResponse.from(token));
    }

    @Operation(summary = "로그아웃")
    @DeleteMapping("/auth")
    public BackofficeResponse<Void> logout(Principal principal) {
        if(principal == null) {
            throw new AuthenticationException("사용자 정보를 찾을 수 없습니다.");
        }

        log.info("AUTH:DEL_:RQST: 토큰 삭제 요청. [email={}]", principal.getName());
        String email = principal.getName();
        backofficeAuthService.invalidateToken(email);

        return new BackofficeResponse<>();
    }
}
