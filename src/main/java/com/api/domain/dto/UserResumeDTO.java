package com.api.domain.dto;

import java.time.LocalDate;

public record UserResumeDTO (
        Long id,
        String name,
        String email,
        LocalDate createdOn,
        LocalDate updatedOn
)
{ }