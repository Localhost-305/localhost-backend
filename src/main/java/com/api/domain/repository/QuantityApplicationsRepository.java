package com.api.domain.repository;

import com.api.domain.entity.FactApplication;
import com.api.domain.entity.FactHiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityApplicationsRepository extends JpaRepository<FactApplication, Long> {

    @Query(value = """
               SELECT
                               dd.year AS year,
                               dd.month AS month,
                               CASE
                                   WHEN :profession IS NOT NULL THEN dj.job_title
                                   ELSE NULL
                               END AS jobTitle,
                               SUM(fh.number_of_applications) AS applications,
                               DENSE_RANK() OVER (ORDER BY dd.year, dd.month) AS `rank`
                           FROM
                               fact_applications fh
                           LEFT JOIN
                               dim_date dd ON dd.date_id = fh.date_id
                           LEFT JOIN
                               dim_jobs dj ON fh.job_id = dj.job_id
                           WHERE
                               (dd.year * 100 + dd.month) BETWEEN
                                   ((YEAR(DATE_SUB(CURRENT_DATE, INTERVAL :months MONTH)) * 100) + MONTH(DATE_SUB(CURRENT_DATE, INTERVAL :months MONTH)))\s
                                   AND
                                   (YEAR(CURRENT_DATE) * 100 + MONTH(CURRENT_DATE))
                               AND
                                   (:profession IS NULL OR dj.job_title = :profession)
                           GROUP BY
                               dd.year, dd.month,
                               CASE
                                   WHEN :profession IS NOT NULL THEN dj.job_title
                                   ELSE NULL
                               END
                           ORDER BY
                               dd.year, dd.month;
            """, nativeQuery = true)
    List<Object[]> findApplicationsByMonths(@Param("months") int months,@Param("profession") String profession );

}
