package com.api.domain.service;

import com.api.domain.entity.FactApplication;
import com.api.domain.repository.FactApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactApplicationService {

    @Autowired
    private FactApplicationRepository repository;

    public List<FactApplication> findAll() {

        List<FactApplication> factApplications = repository.findAll();

        factApplications = tratarDados(factApplications);

        return factApplications;

    }

    private List<FactApplication> tratarDados(List<FactApplication> applications) {

        return applications;
    }
}
