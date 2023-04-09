package com.miridih.library.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miridih.library.auth.exception.ExpiredTokenException;
import com.miridih.library.auth.exception.InvalidTokenException;
import com.miridih.library.core.ui.response.BackofficeResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredTokenException | InvalidTokenException e) { // 만료된 토큰 + 유효하지 않은 토큰
            createErrorResponse(response, e);
        }
    }

    private void createErrorResponse(HttpServletResponse response, Exception e) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response
                .getWriter()
                .write(objectMapper.writeValueAsString(
                        BackofficeResponse.of(ErrorStatus.E1, e.getMessage())
                ));
    }
}
