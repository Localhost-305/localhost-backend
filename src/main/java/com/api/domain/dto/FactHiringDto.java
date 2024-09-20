package com.api.domain.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.Date;

public record FactHiringDto (
    Integer candidateId,
    Integer jobId,
    Integer dateId,
    Integer hourId,
    Date hiringDate,
    Double initialSalary,
    String contractType,
    LocalDateTime acceptanceDate,
    Integer qtyHiring
){
}
