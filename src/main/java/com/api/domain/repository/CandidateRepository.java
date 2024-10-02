package com.api.domain.repository;

import com.api.domain.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
<<<<<<< HEAD


    Candidate findAllByCandidateId(Long candidateId);


=======

    List<Candidate> getAll();
>>>>>>> 5fb4239d6c47d8b9eaaed63385ff74501a8c47a7
}
