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
                DATE_FORMAT(fh.hiring_date, '%Y-%m') AS period,  
                SUM(fh.initial_salary * 0.15) AS collected_revenue, 
                DENSE_RANK() OVER (ORDER BY DATE_FORMAT(fh.hiring_date, '%Y-%m')) AS `rank`
            FROM  
                fact_hirings fh 
            WHERE  
                fh.hiring_date BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL :months MONTH) AND CURRENT_DATE 
            GROUP BY  
                period 
            ORDER BY  
                period;
            """, nativeQuery = true)


    List<Object[]> findAmountCollectedByMonths(@Param("months") int months);


}

