package com.api.domain.service;

import com.api.domain.entity.Hour;
import com.api.domain.repository.HourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HourService {

    @Autowired
    private HourRepository repository;
    public List<Hour> getAllHours() {return repository.findAll();}
}
