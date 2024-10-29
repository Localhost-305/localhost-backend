package com.api.domain.repository;

import com.api.domain.entity.FactHiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmonuntCollectedRepository extends JpaRepository<FactHiring,Long> {
}

