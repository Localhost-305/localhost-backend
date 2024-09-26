package com.api.domain.service;

import com.api.domain.entity.RecruitmentProcess;
import com.api.domain.repository.RecruitmentProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentProcessService {

    @Autowired
    private RecruitmentProcessRepository repository;



}
