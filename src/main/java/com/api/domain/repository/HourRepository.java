package com.api.domain.repository;

import com.api.domain.entity.Candidate;
import com.api.domain.entity.Hour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HourRepository extends JpaRepository<Hour, Long> {
<<<<<<< HEAD
    Hour getAllByHourId(Long hourId);
}

=======
    List<Hour> getAll();
}
>>>>>>> 5fb4239d6c47d8b9eaaed63385ff74501a8c47a7
