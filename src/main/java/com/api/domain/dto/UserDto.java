package com.api.domain.dto;

import com.api.domain.entity.Permission;
import com.api.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record UserDto(
        @NotBlank String name,
        @Email String email,
        @NotBlank String password,
        LocalDate createdOn,
        LocalDate updatedOn,
        String role,
        List<String> permissions
) {}