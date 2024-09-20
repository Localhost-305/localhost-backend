package com.api.domain.entity;

import com.api.domain.dto.JobDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    @Column(name = "created_on") // use only to convert Camel Case (createdOn) to Snake Case (created_on)
    private LocalDate createdOn;
    @Column(name = "updated_on")
    private LocalDate updatedOn;

    public Job(@Valid JobDto jobDto) {
        this.jobTitle = jobDto.jobTitle();
        this.numberOfPositions = jobDto.numberOfPositions();
        this.jobRequirements = jobDto.jobRequirements();
        this.jobStatus = jobDto.jobStatus();
        this.location = jobDto.location();
        this.responsiblePerson = jobDto.responsiblePerson();
        this.createdOn = LocalDate.now();
        this.updatedOn = LocalDate.now();
    }
}
