package com.api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "FactHirings")
@Table(name = "fact_hirings")
@NoArgsConstructor
@Data
public class FactHiring {

    @EmbeddedId
    private FactHiringId id;

    @Column(name = "hiring_date")
    private Date hiringDate;
    @Column(name = "initial_salary")
    private Double initialSalary;
    @Column(name = "contract_type")
    private String contractType;
    @Column(name = "acceptance_date")
    private LocalDateTime acceptanceDate;
    @Column(name = "qty_hirings")
    private Integer qtyHirings;
}
