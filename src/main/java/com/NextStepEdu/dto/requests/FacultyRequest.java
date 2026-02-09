package com.NextStepEdu.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FacultyRequest(
        @NotBlank(message = "Faculty name is required")
        String name,
        String description,
        @NotNull(message = "universityId is required")
        UUID universityId
) {
}
