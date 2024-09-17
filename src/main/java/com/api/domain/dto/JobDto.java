package com.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record JobDto(
    @NotBlank
    String jobTitle,
    @NotBlank
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
