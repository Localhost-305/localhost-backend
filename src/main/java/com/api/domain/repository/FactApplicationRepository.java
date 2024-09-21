package com.api.domain.repository;

import com.api.domain.entity.FactApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactApplicationRepository extends JpaRepository<FactApplication, Long> {
    FactApplication findByName(String name);
}
