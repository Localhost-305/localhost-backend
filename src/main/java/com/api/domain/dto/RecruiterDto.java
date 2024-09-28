package com.api.domain.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecruiterDto(
        @NotBlank
        String recruiterName,
        @NotBlank
        String recruiterRole,
        @NotNull
        Integer recruiterFeedbacksGiven
)
{
}
