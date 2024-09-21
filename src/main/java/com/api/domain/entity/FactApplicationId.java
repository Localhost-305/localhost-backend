package com.api.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FactApplicationId implements Serializable {
    private Integer recruiterId;
    private Integer candidateId;
    private Integer jobId;
    private Integer processId;
    private Integer dateId;
    private Integer hourId;
}
