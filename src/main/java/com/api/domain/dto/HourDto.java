package com.api.domain.dto;

import jakarta.validation.constraints.NotNull;

public record HourDto(
        @NotNull
        Integer hour
){
}
