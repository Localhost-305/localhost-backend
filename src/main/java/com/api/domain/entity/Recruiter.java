package com.api.domain.entity;

import com.api.domain.dto.RecruiterDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Recruiters")
@Table(name = "dim_recruiters")
@NoArgsConstructor
@Data

public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruiter_id")
    private Long recruiterId;
    @Column(name = " recruiter_name")
    private String recruiterName;
    @Column(name = "role")
    private String recruiterRole;
    @Column(name = "feedbacks_given")
    private Integer recruiterFeedbacksGiven;

    public Recruiter(@Valid RecruiterDto recruiterDto) {
        this.recruiterName = recruiterDto.recruiterName();
        this.recruiterRole = recruiterDto.recruiterRole();
        this.recruiterFeedbacksGiven = recruiterDto.recruiterFeedbacksGiven();
    }
}
