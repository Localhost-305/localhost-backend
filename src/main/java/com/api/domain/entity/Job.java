package com.api.domain.entity;

import com.api.domain.dto.JobDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "Jobs")
@Table(name = "dim_jobs")
@NoArgsConstructor
@Data

public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "number_of_positions")
    private Integer numberOfPositions;
    @Column(name = "job_requirements")
    private String jobRequirements;
    @Column(name = "job_status")
    private String jobStatus;
    private String location;
    @Column(name = "responsible_person")
    private String responsiblePerson;

    public Job(@Valid JobDto jobDto) {
        this.jobTitle = jobDto.jobTitle();
        this.numberOfPositions = jobDto.numberOfPositions();
        this.jobRequirements = jobDto.jobRequirements();
        this.jobStatus = jobDto.jobStatus();
        this.location = jobDto.location();
        this.responsiblePerson = jobDto.responsiblePerson();
    }
}
