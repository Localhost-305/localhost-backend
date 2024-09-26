package com.api.domain.dto;

public record DataTokenJWTDTO(
        String tokenJWT,
        UserResumeDTO user
) {}