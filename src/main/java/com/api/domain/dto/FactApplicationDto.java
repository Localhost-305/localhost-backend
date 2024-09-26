package com.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FactApplicationDto (
        @NotNull
        Integer numberOfApplications,
        @NotNull
        Integer recruiterId,
        @NotNull
        Integer candidateId,
        @NotNull
        Integer jobId,
        @NotNull
        Integer processId,
        @NotNull
        Integer dateId,
        @NotNull
        Integer hourId
)
{
}
