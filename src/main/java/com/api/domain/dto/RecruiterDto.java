package com.api.domain.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record RecruiterDto(
        @NotBlank
        String recruiterName,
        @NotBlank
        String recruiterRole,
        @NotBlank
        int recruiterFeedbacksGiven
)
{
}
