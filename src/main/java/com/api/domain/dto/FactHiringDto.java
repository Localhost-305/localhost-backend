package com.api.domain.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;
import java.util.Date;

public record FactHiringDto (
        @NotNull
        Integer candidateId,
        @NotNull
        Integer jobId,
        @NotNull
        Integer dateId,
        @NotNull
        Integer hourId,
        @Past
        Date hiringDate,
        @NotNull
        Double initialSalary,
        @NotBlank
        String contractType,
        @PastOrPresent
        LocalDateTime acceptanceDate,
        Integer qtyHiring
){
}
