package com.api.domain.dto;

import com.api.domain.entity.User;

public record DataTokenJWTDTO(
        String tokenJWT,
        User user
) {}