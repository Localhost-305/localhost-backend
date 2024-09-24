package com.api.controller;

import com.api.domain.entity.Candidate;
import com.api.domain.entity.FactApplication;
import com.api.domain.service.FactApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/factApplication")
public class FactApplicationController {

    @Autowired
    private FactApplicationService factApplicationService;

    @GetMapping
    public ArrayList<Object> getAll(@RequestParam ("StartDate") String startDateStr , @RequestParam("EndDate") String endDateStr) {

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        return factApplicationService.findAll(startDate,endDate);
    }

}

