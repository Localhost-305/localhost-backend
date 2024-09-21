package com.api.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import java.util.Date;

public record CandidateDto(
        @NotBlank
        String candidate_name,
        @NotBlank
        String email,
        @NotBlank
        String phone,
        @NotBlank
        Date birthDate
) {}