package com.miridih.library.auth.application;

import com.miridih.library.auth.domain.JwtToken;
import com.miridih.library.auth.exception.ExpiredTokenException;
import com.miridih.library.auth.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final long ACCESS_TOKEN_EXPIRES_IN = 15778476000L; // 6개월
    private static final long REFRESH_TOKEN_EXPIRES_IN = 31556952000L; // 1년
    private static final String AUTH_KEY = "auth";
    private static final String AUTH_KEY_DELIMITER = ",";
    private static final String AUTH_SCHEME = "Bearer";
    private static final SignatureAlgorithm DEFAULT_SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private final Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String key) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    public String createAccessToken(Authentication authentication) {
        long now = new Date().getTime();
        Date expireTime = new Date(now + ACCESS_TOKEN_EXPIRES_IN);

        return createAccessToken(
                authentication.getName(),
                createAuthority(authentication.getAuthorities()),
                expireTime
        );
    }

    public String createAccessToken(String email, String authorities, Date expireTime) {
        return Jwts
                .builder()
                .setSubject(email)
                .claim(AUTH_KEY, authorities)
                .setExpiration(expireTime)
                .signWith(key, DEFAULT_SIGNATURE_ALGORITHM)
                .compact();
    }

    public JwtToken createJwtToken(Authentication authentication) {
        String auth = createAuthority(authentication.getAuthorities());

        long now = new Date().getTime();
        Date accessTokenExpireTime = new Date(now + ACCESS_TOKEN_EXPIRES_IN);
        Date refreshTokenExpireTime = new Date(now + REFRESH_TOKEN_EXPIRES_IN);

        final String accessToken = createAccessToken(authentication.getName(), auth, accessTokenExpireTime);
        final String refreshToken = Jwts
                .builder()
                .setExpiration(refreshTokenExpireTime)
                .signWith(key, DEFAULT_SIGNATURE_ALGORITHM)
                .compact();

        return JwtToken
                .builder()
                .grantType(AUTH_SCHEME)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseFrom(token);

        if(claims.get(AUTH_KEY) == null) {
            throw new InvalidTokenException("AUTH_KEY 가 비어있습니다.", token);
        }

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTH_KEY).toString().split(AUTH_KEY_DELIMITER))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());


        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("만료된 토큰입니다.", token);
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException  e) {
            throw new InvalidTokenException("유효하지 않은 토큰입니다.", e, token);
        }
    }

    private String createAuthority(Collection<? extends GrantedAuthority> authorities) {
        return authorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(AUTH_KEY_DELIMITER));
    }

    private Claims parseFrom(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("만료된 토큰입니다.", token);
        }
    }
}
