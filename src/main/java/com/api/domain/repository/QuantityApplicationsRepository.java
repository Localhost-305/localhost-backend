package com.api.domain.repository;


import com.api.domain.entity.FactHiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface QuantityApplicationsRepository extends JpaRepository<FactHiring, Long> {

    @Query(value = "SELECT(count)(candidate_id) as allCandidate  FROM FactHirings",nativeQuery = true)
    Long countAllCandidates();


}


