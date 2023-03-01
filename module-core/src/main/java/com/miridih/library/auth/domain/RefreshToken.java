package com.miridih.library.auth.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    private String key;
    private String value;

    public static RefreshToken of(String key, String value) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.key = key;
        refreshToken.value = value;

        return refreshToken;
    }

}
