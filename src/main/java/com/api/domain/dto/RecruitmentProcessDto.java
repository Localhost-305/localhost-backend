package com.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record RecruitmentProcessDto(
        @NotBlank
        String processName,
        @NotBlank
        Date startDate,
        @NotBlank
        Date endDate,
        @NotBlank
        String processDescription
)
{}
