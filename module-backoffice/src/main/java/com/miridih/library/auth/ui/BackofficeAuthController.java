package com.miridih.library.auth.ui;

import com.miridih.library.auth.application.BackofficeAuthService;
import com.miridih.library.auth.domain.JwtToken;
import com.miridih.library.auth.ui.request.TokenAccessRequest;
import com.miridih.library.auth.ui.request.TokenRefreshRequest;
import com.miridih.library.auth.ui.response.TokenResponse;
import com.miridih.library.core.ui.response.BackofficeResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeAuthController {

    private final BackofficeAuthService backofficeAuthService;

    @PostMapping("/auth")
    public BackofficeResponse<TokenResponse> accessToken(@RequestBody TokenAccessRequest request) {
        log.info("AUTH:TOKN:RQST: 토큰 발급 요청. [request={}]", request);

        try {
            JwtToken token = backofficeAuthService.issueToken(request.getEmail(), request.getPassword());

            return BackofficeResponse.of(TokenResponse.from(token));
        } catch (Exception e) {
            log.error("AUTH:TOKN:FAIL: 토큰 발급중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @PutMapping("/auth")
    public BackofficeResponse<TokenResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        log.info("AUTH:RFSH:RQST: 토큰 재발급 요청. [request={}]", request);

        try {
            JwtToken token =  backofficeAuthService.reIssueToken(request.getAccessToken(), request.getRefreshToken());

            return BackofficeResponse.of(TokenResponse.from(token));
        } catch (Exception e) {
            log.error("AUTH:RFSH:FAIL: 토큰 재발급중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @DeleteMapping("/auth")
    public BackofficeResponse<Void> logout(Principal principal) {
        if(principal == null) {
            return BackofficeResponse.of(ErrorStatus.E1, "사용자를 찾을 수 없습니다.");
        }

        log.info("AUTH:DEL_:RQST: 토큰 삭제 요청. [email={}]", principal.getName());
        try {
            // refresh_token 제거 처리
            String email = principal.getName();
            backofficeAuthService.invalidateToken(email);

            return new BackofficeResponse<>();
        } catch (Exception e) {
            log.error("AUTH:DEL_:FAIL: 토큰 삭제중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }
}
