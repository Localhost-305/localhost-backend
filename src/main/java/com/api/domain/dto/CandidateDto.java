package com.api.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CandidateDto(
        @NotBlank
        String candidate_name;
        @NotBlank
        String phone;
        @NotBlank
        Date birth_date;
) {}