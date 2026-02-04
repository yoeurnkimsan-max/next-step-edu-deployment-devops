package com.NextStepEdu.exceptions;

import lombok.Builder;

@Builder
public record FieldError(
        String field,
        String detail
) {

}
