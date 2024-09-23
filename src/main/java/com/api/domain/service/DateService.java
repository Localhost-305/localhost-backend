package com.api.domain.service;

import com.api.domain.entity.Date;
import com.api.domain.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateService {

    @Autowired
    private DateRepository repository;

    public List<Date> getAllDates() { return repository.findAll(); }
}
