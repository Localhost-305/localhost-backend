package com.api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Date;
import com.api.domain.dto.CandidateDto;

@Entity(name="Candidates")
@Table(name="dim_candidates")
@NoArgsConstructor // Implements constructor only: new User(); don't user AllArgsContructor
@Data // Implements GET's and SET's
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long candidateId;
    @Column(name = "candidate_name")
    private String candidateName;
    private String email;
    private String phone;
    private Date birthDate;
    @Column(name = "created_on") // use only to convert Camel Case (createdOn) to Snake Case (created_on)
    private LocalDate createdOn;
    @Column(name = "updated_on")
    private LocalDate updatedOn;

    public Candidate(@Valid CandidateDto CandidateDto){
        this.candidateName = CandidateDto.candidate_name();
        this.email = CandidateDto.email();
        this.phone = CandidateDto.phone();
        this.birthDate = CandidateDto.birthDate();
        this.createdOn = LocalDate.now();
        this.updatedOn = LocalDate.now();
    }

}
