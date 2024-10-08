package com.api.domain.repository;

import com.api.domain.entity.FactApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface FactApplicationRepository extends JpaRepository<FactApplication, Long> {
 @Query(value = "select j.job_title,count(*) from fact_applications f left join dim_jobs j on j.job_id = f.job_id Where :startDate<=j.opening_date and :endDate>=j.closing_date  group By j.job_title" , nativeQuery = true)
 List<Object[]> getAllFactorUserByDate(@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);


// @Query(value = "select j.job_title,COUNT(a.candidate_id) AS numero_de_candidatos from fact_applications f left join dim_jobs j on j.job_id = f.job_id Where :startDate<=j.opening_date and :endDate>=j.closing_date  group By j.job_title" , nativeQuery = true)
// List<Object[]> getAllFactorCandidateByDate(@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);

 @Query(value = "SELECT j.job_title, COUNT(a.candidate_id) AS numero_de_candidatos " +
         "FROM fact_applications a " +
         "INNER JOIN dim_jobs j ON a.job_id = j.job_id " +
         "INNER JOIN dim_date d ON a.date_id = d.date_id " +
         "WHERE :startDate <= j.opening_date AND :endDate >= j.closing_date " +
         "GROUP BY j.job_title", nativeQuery = true)
 List<Object[]> getAllFactorCandidateByDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
