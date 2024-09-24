package com.api.domain.service;

import com.api.domain.util.MapObjectList;
import com.api.domain.repository.FactApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class FactApplicationService {

    @Autowired
    private FactApplicationRepository repository;

    public ArrayList<Object> findAll(LocalDate startDate , LocalDate endDate) {

        String[] colums ={"jobTitle", "count"};
        return MapObjectList.mapObjectList(repository.getAllFactorUser(startDate, endDate),colums);

    }
}
