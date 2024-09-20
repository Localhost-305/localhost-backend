package com.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record FactApplicationDto (
        @NotBlank
        Integer numberOfApplications,
        @NotBlank
        Integer recruiterId,
        @NotBlank
        Integer candidateId,
        @NotBlank
        Integer jobId,
        @NotBlank
        Integer processId,
        @NotBlank
        Integer dateId,
        @NotBlank
        Integer hourId
)
{
}
