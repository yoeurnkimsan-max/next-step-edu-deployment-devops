package com.NextStepEdu.exceptions;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse<T> {
    private Integer code;
    T reason;
}