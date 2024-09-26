package com.api.domain.repository;

import com.api.domain.entity.Recruiter;
import com.api.domain.entity.RecruitmentProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
    Recruiter findByRecruiterId(Long recruiterId);
}
