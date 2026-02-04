package com.NextStepEdu.dto.responses;

import lombok.Builder;

@Builder
public record AuthResponse(
        //Token type
        String tokenType,

        String accessToken,

        String refreshToken
) {
}