package com.api.domain.repository;

import com.api.domain.entity.RecruitmentProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentProcessRepository extends JpaRepository<RecruitmentProcess, Long> {
    RecruitmentProcess findAllById(Long recruitmentProcessId);
}
