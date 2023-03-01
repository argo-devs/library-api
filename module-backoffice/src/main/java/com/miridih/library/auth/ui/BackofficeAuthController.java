package com.miridih.library.auth.ui;

import com.miridih.library.auth.application.BackofficeAuthService;
import com.miridih.library.auth.domain.JwtToken;
import com.miridih.library.auth.ui.request.TokenAccessRequest;
import com.miridih.library.auth.ui.request.TokenRefreshRequest;
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
    public JwtToken accessToken(@RequestBody TokenAccessRequest request) {
        log.info("ACCESS TOKEN with request={}", request);

        return backofficeAuthService.issueToken(request.getEmail(), request.getPassword());
    }

    @PutMapping("/auth")
    public JwtToken refreshToken(@RequestBody TokenRefreshRequest request) {
        log.info("REFRESH TOKEN");

        return backofficeAuthService.reIssueToken(request.getAccessToken(), request.getRefreshToken());
    }

    @DeleteMapping("/auth")
    public void logout(Principal principal) {
        if(principal == null) {
            throw new RuntimeException("사용자 정보가 없습니다.");
        }

        // refresh_token 제거 처리
        String email = principal.getName();
        backofficeAuthService.invalidateToken(email);
    }
}
