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
    private Integer candidate_id;
    private Integer job_id;
    private Integer date_id;
    private Integer hour_id;
}
