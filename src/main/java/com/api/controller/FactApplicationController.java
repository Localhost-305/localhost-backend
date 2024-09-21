package com.api.controller;

import com.api.domain.entity.Candidate;
import com.api.domain.entity.FactApplication;
import com.api.domain.service.FactApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/factApplication")
public class FactApplicationController {

    @Autowired
    private FactApplicationService factApplicationService;

    @GetMapping
    public List<FactApplication> getAll() {
        return factApplicationService.findAll();
    }

}

