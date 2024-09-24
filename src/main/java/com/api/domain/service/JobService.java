package com.api.domain.service;

import com.api.domain.dto.AverageByJobDto;
import com.api.domain.entity.Job;
import com.api.domain.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    public List<Job> findAll() {

        List<Job> jobs = repository.findAll();
        return jobs;
    }

    public List<AverageByJobDto> getJobAverager(Optional<Date> startDate, Optional<Date> endDate) {

        List<AverageByJobDto> AverageByJobDtos = new ArrayList<>();
        if(startDate.isPresent()) {
            startDate = Optional.of(new Date());
        }
        if (endDate.isPresent()){
            endDate = Optional.of(new Date());
        }

        List<Object[]> obj = repository.getAverage(startDate.get(), endDate.get());

        for (Object[] o : obj) {
            AverageByJobDto start = new AverageByJobDto((String)o[0], (Integer)o[1]);
            AverageByJobDtos.add(start);
        }

        return AverageByJobDtos;
    }
}
