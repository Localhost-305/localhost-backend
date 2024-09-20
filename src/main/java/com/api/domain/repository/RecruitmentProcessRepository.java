package com.api.domain.repository;

import com.api.domain.entity.RecruitmentProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentProcessRepository extends JpaRepository<RecruitmentProcess, Long> {
    RecruitmentProcess findAllById(Long recruitmentProcessId);
}
