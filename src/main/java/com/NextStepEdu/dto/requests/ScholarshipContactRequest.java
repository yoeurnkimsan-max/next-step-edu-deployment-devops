package com.NextStepEdu.dto.requests;

import jakarta.validation.constraints.NotNull;

public record ScholarshipContactRequest(

        String label,

        String email,

        String phone,

        String websiteUrl,

        @NotNull(message = "Scholarship ID is required")
        Integer scholarshipId

) {
}
