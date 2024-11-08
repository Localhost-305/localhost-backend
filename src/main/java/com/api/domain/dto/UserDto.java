package com.api.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank String name,
        @Email String email,
        @NotBlank(groups = Create.class) String password,
        @NotBlank(groups = Create.class) String role
) {
    public interface Create {}
    public interface Update {}
    public interface UpdateRole{}
}