package com.api.domain.entity;

import com.api.domain.dto.RecruitmentProcessDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "RecruitmentProcesses")
@Table(name = "dim_recruitment_processes")
@NoArgsConstructor
@Data

public class RecruitmentProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "process_id")
    private Long id;
    @Column(name = "process_name")
    private String processName;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "process_descriptiom")
    private String processDescription;
    @Column(name = "created_on") // use only to convert Camel Case (createdOn) to Snake Case (created_on)
    private LocalDate createdOn;
    @Column(name = "updated_on")
    private LocalDate updatedOn;

    public RecruitmentProcess(@Valid RecruitmentProcessDto recruitmentProcessDto) {
        this.processName = recruitmentProcessDto.processName();
        this.startDate = recruitmentProcessDto.startDate();
        this.endDate = recruitmentProcessDto.endDate();
        this.processDescription = recruitmentProcessDto.processDescription();
    }
}
