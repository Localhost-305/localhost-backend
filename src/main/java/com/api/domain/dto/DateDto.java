package com.api.domain.dto;
import jakarta.validation.constraints.NotNull;

public record DateDto (
        @NotNull
        Integer dateDay,
        @NotNull
        Integer dateMonth,
        @NotNull
        Integer dateYear
){
}
