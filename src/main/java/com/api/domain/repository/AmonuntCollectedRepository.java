package com.api.domain.repository;

import com.api.domain.dto.AmountCollectedDto;
import com.api.domain.entity.FactHiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;

@Repository
public interface AmonuntCollectedRepository extends JpaRepository<FactHiring, Long> {

    @Query(value = """ 
            SELECT 
                                 YEAR(fh.hiring_date) AS year, 
                                 MONTH(fh.hiring_date) AS month, 
                                 CASE
                                     WHEN :profissao IS NOT NULL THEN dj.job_title
                                     ELSE NULL
                                 END AS jobTitle, 
                                 SUM(fh.initial_salary * 0.15) AS collected_revenue,
                                 DENSE_RANK() OVER (ORDER BY YEAR(fh.hiring_date), MONTH(fh.hiring_date)) AS `rank`
                             FROM 
                                 fact_hirings fh
                             JOIN
                                 dim_jobs dj ON fh.job_id = dj.job_id
                             WHERE 
                                 fh.hiring_date BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL :months MONTH) AND CURRENT_DATE
                                 AND (:profissao IS NULL OR dj.job_title = :profissao)
                             GROUP BY 
                                 year, month,
                                 CASE
                                     WHEN :profissao IS NOT NULL THEN dj.job_title
                                     ELSE NULL
                                 END
                             ORDER BY 
                                 year, month;
            """, nativeQuery = true)
    List<Object[]> findAmountCollectedByMonths(@Param("months") int months,@Param("profissao") String profissao );


}

