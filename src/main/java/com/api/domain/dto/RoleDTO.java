package com.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleDTO(
        @NotBlank
        String roleName
) {}
