package com.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record DateDto (
        @NotBlank
        Integer dateDay,
        Integer dateMonth,
        Integer dateYear
){
}
