package com.api.domain.repository;

import com.api.domain.entity.FactApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface FactApplicationRepository extends JpaRepository<FactApplication, Long> {
 @Query (value = "select j.job_title,count(*) from fact_applications f left join dim_jobs j on j.job_id = f.job_id Where :startDate<=j.opening_date and :endDate>=j.closing_date  group By j.job_title" , nativeQuery = true)
 List<Object[]> getAllFactorUser(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
}
