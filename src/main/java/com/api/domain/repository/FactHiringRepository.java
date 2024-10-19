package com.api.domain.repository;

import com.api.domain.entity.FactHiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface FactHiringRepository extends JpaRepository<FactHiring, Long> {

    @Query("SELECT fh FROM FactHirings fh WHERE fh.hiringDate BETWEEN :startDate AND :endDate")
    List<FactHiring> findByHiringDateBetween(Date startDate, Date endDate);

    @Query(value = """
        SELECT CONCAT(MONTH(hiring_date), '/', YEAR(hiring_date)) AS month_year,
            AVG(DATEDIFF(IFNULL(contract_end_date, CURDATE()), hiring_date)) AS average_retention_days
        FROM fact_hirings
        WHERE hiring_date BETWEEN ? AND ?
        GROUP BY CONCAT(MONTH(hiring_date), '/', YEAR(hiring_date))
        """, nativeQuery = true)
    List<Map<String, Object>> calculateRetentionRate(LocalDate startDate, LocalDate endDate);

}
