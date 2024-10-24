package com.api.domain.dto;

import com.api.domain.Enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record UserDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        Set<Role> roles // Mude de String para Role
) {}