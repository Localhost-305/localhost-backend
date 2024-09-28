package com.api.domain.repository;

import com.api.domain.entity.Candidate;
import com.api.domain.entity.Hour;
import com.api.domain.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HourRepository extends JpaRepository<Hour, Long> {


}
