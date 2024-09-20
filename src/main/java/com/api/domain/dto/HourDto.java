package com.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record HourDto(
        @NotBlank
        Integer hour
){
}
