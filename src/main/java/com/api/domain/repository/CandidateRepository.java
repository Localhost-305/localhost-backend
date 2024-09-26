package com.api.domain.repository;

import com.api.domain.entity.Candidate;
import com.api.domain.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Candidate findByCandidateId(Long candidate_id);
}
