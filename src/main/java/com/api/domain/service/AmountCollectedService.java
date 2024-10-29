package com.api.domain.service;

import com.api.domain.repository.AmonuntCollectedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmountCollectedService {

    @Autowired
    private AmonuntCollectedRepository amonuntCollectedRepository;

    public List findAll (){

        return amonuntCollectedRepository.findAll();
    }


}
