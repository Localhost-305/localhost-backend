package com.api.domain.repository;

import com.api.domain.entity.Hour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourRepository extends JpaRepository<Hour, Long> {
    Hour getAllByHourId(Long hourId);
}

