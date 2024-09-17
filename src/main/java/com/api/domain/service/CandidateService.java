package com.api.domain.service;

import com.api.domain.entity.Candidate;
import com.api.domain.repository.CandidateRepository;
import com.api.domain.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class CandidateService {

    @Autowired
    private CandidateRepository repository;

    public List<Candidate> getAllCandidates() { return repository.findAll(); }
}
