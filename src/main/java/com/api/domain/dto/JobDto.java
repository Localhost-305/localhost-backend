package com.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JobDto(
    @NotBlank
    String jobTitle,
    @NotNull
    Integer numberOfPositions,
    @NotBlank
    String jobRequirements,
    @NotBlank
    String jobStatus,
    @NotBlank
    String location,
    @NotBlank
    String responsiblePerson
)
{}
