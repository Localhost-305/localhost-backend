package com.api.domain.repository;

import com.api.domain.entity.FactHiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FactHiringRepository extends JpaRepository<FactHiring, Long> {

    @Query("SELECT fh FROM FactHirings fh WHERE fh.hiringDate BETWEEN :startDate AND :endDate")
    List<FactHiring> findByHiringDateBetween(Date startDate, Date endDate);
}
