package com.api.domain.repository;

import com.api.domain.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query(value = """
        SELECT
        j.job_title,
        ROUND(AVG(DATEDIFF(j.closing_date, j.opening_date))) AS tempo_medio_contratacao_por_vaga_dias
        FROM
        dim_jobs j
        WHERE
        j.opening_date BETWEEN ?1 AND ?2
        GROUP BY
        j.job_title;
        """, nativeQuery = true)

    List<Object[]> getAverage (@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);

    @Query(value = """
            SELECT
                ROUND(AVG(DATEDIFF(j.closing_date, j.opening_date))) AS tempo_medio_contratacao_dias FROM  dim_jobs j
            WHERE j.opening_date BETWEEN ?1 AND ?2;
            """, nativeQuery = true)
    List<Object[]> getAverageAll (@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);

}
