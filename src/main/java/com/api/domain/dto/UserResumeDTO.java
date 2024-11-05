package com.api.domain.dto;

import java.time.LocalDate;
import java.util.List;

public record UserResumeDTO (
        Long id,
        String name,
        String email,
        String role,
        Long roleId,
        List<String> permissions
)
{ }