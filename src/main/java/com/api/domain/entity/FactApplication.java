package com.api.domain.entity;

import com.api.domain.dto.FactApplicationDto;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "FactApplications")
@Table(name = "fact_applications")
@NoArgsConstructor
@Data
public class FactApplication {

    @EmbeddedId
    private FactApplicationId id;

    @Column(name = "number_of_applications")
    private Integer numberOfApplications;

    public FactApplication(@Valid FactApplicationDto factApplicationDto) {
        this.id = new FactApplicationId(
                factApplicationDto.recruiterId(),
                factApplicationDto.candidateId(),
                factApplicationDto.jobId(),
                factApplicationDto.processId(),
                factApplicationDto.dateId(),
                factApplicationDto.hourId()
        );
    }
}
