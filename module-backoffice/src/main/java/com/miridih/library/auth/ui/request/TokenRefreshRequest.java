package com.miridih.library.auth.ui.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenRefreshRequest {
    @Schema(description = "엑세스 토큰", example = "jsdhjb1hj2begq98da98hdn1d8qh")
    @NotBlank
    private String accessToken;

    @Schema(description = "리프레시 토큰", example = "daisd09j12oken109duud2b33")
    @NotBlank
    private String refreshToken;
}
