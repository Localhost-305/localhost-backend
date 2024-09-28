package com.api.domain.service;

import com.api.domain.entity.Recruiter;
import com.api.domain.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository repository;

    public List<Recruiter> getAllRecruiters() {return repository.findAll();}
}
