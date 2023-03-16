package com.miridih.library.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTH_SCHEME = "Bearer ";
    private static final String AUTHORIZATION_HEADER = HttpHeaders.AUTHORIZATION;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 토큰 추출
        String jwtToken = retrieveToken(request);

        // 토큰 검증 및 등록
        if(jwtToken != null) {
            jwtTokenProvider.validateToken(jwtToken);

            Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(token) && token.startsWith(AUTH_SCHEME)) {
            return token.substring(AUTH_SCHEME.length());
        }

        return null;
    }
}
