package com.api.domain.service;

import com.api.domain.entity.Job;
import com.api.domain.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    public List<Job> getAllJobs() {return repository.findAll();}
}
