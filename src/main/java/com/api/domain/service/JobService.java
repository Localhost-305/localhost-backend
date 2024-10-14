package com.api.domain.service;

import com.api.domain.util.MapObjectList;
import com.api.domain.entity.Job;
import com.api.domain.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;
    public List<Job> findAll() {

        List<Job> jobs = repository.findAll();
        return jobs;
    }

    public ArrayList<Object> findAll(LocalDate startDate , LocalDate endDate) {

        String[] colums ={"JobTitle", "AverageTime"};
        return MapObjectList.mapObjectList(repository.getAverage(startDate, endDate),colums);

    }

    public ArrayList<Object> getAverageAll(LocalDate startDate , LocalDate endDate) {

        String[] colums ={"AverageTime"};
        return MapObjectList.mapObjectList(repository.getAverageAll(startDate, endDate),colums);

    }

    public ArrayList<Object> getCandidateByJob(String jobTitle) {

        String[] colums ={"JobTitle"};
        return MapObjectList.mapObjectList(repository.getCandidateByJob(jobTitle),colums);
    }


}
