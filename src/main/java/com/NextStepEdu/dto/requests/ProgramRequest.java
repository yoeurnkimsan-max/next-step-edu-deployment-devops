package com.NextStepEdu.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgramRequest {
    @NotBlank(message = "Program name is required")
    private String name;
    
    private String description;
    private Integer degreeLevel;
    private Boolean examRequired;
    private Double tuitionFeeAmount;
    private String currency;
    private Integer studyPeriodMonths;
    
    @NotNull(message = "University ID is required")
    private Integer universityId;
    
    private Integer facultyId;
}
