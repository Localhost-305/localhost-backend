package com.api.domain.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record RecruitmentProcessDto(
        @NotBlank
        String processName,
        @Past
        Date startDate,
        @PastOrPresent
        Date endDate,
        @NotBlank
        String processDescription
)
{}
