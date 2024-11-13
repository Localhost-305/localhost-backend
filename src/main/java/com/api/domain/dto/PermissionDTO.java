package com.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record PermissionDTO(
        @NotBlank
        String permissionName
) {
}
