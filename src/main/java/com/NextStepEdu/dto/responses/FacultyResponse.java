package com.NextStepEdu.dto.responses;

import java.util.UUID;

public record FacultyResponse(
        UUID id,
        String name,
        String description,
        UUID universityId,
        String universityName
) {
}
