package com.api.domain.repository;


import com.api.domain.entity.FactHiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface QuantityApplicationsRepository extends JpaRepository<FactHiring, Long> {

//    @Query(value = "SELECT fh.candidate_id AS candidateId, fh.hiring_date AS hiringDate " +
//            "FROM fact_hirings fh " +
//            "WHERE fh.hiring_date BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL :months MONTH) AND CURRENT_DATE",
//            nativeQuery = true)
//    List<Object[]> findAllCandidatesAndHiringDatesByMonths(@Param("months") int months);

    @Query(value = "SELECT fh.hiring_date AS hiringDate, SUM(fh.qty_hirings) AS totalHirings " +
            "FROM fact_hirings fh " +
            "WHERE fh.hiring_date BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL :months MONTH) AND CURRENT_DATE " +
            "GROUP BY fh.hiring_date " +
            "ORDER BY fh.hiring_date",
            nativeQuery = true)
    List<Object[]> findAllHiringDatesAndTotalHiringsByMonths(@Param("months") int months);


}

