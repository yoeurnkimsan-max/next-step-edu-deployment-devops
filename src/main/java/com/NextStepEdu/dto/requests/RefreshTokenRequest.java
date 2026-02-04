package com.NextStepEdu.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(message = "Refresh Token is Required")
        String refreshToken
) {
}