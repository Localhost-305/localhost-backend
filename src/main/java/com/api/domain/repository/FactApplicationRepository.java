package com.api.domain.repository;

import com.api.domain.entity.FactApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FactApplicationRepository extends JpaRepository<FactApplication, Long> {
   @Query
}
