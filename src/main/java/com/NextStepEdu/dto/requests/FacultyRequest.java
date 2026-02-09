package com.NextStepEdu.dto.requests;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record FacultyRequest(
        @NotBlank(message = "Faculty name is required")
        String name,
        String description,
        UUID universityId
) {
}
