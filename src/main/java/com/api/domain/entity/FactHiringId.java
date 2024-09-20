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
public class FactHiringId implements Serializable {
    private Integer candidateId;
    private Integer jobId;
    private Integer dateId;
    private Integer hourId;
}
