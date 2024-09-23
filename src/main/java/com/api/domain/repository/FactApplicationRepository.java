package com.api.domain.repository;

import com.api.domain.entity.FactApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface FactApplicationRepository extends JpaRepository<FactApplication, Long> {
}
