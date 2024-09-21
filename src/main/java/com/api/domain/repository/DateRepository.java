package com.api.domain.repository;

import com.api.domain.entity.Date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<Date, Long> {

}
