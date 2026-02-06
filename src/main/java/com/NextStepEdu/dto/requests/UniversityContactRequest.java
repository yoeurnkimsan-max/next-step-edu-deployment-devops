package com.NextStepEdu.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UniversityContactRequest {
    private String label;
    private String email;
    private String phone;
    private String websiteUrl;

    @NotNull(message = "University ID is required")
    private Integer universityId;
}
