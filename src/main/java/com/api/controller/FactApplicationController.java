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
    public ResponseEntity findAll() {
        return ResponseEntity.ok(factApplicationService.findAll());
    }

    @GetMapping("/jobs")
    public ResponseEntity<ArrayList<Object>> getAllFactorUserByDate(@RequestParam(required = false)  String startDateStr , @RequestParam(required = false)  String endDateStr) {
        if(startDateStr == null){
            startDateStr = "2000-01-01";
        }
        if(endDateStr == null){
            endDateStr = LocalDate.now().toString();
        }
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        return ResponseEntity.ok(factApplicationService.getAllFactorUserByDate(startDate, endDate));
    }


    @GetMapping("/candidate")
    public ResponseEntity<ArrayList<Object>> getAllFactorCandidateByDate(@RequestParam(required = false)  String startDateStr , @RequestParam(required = false)  String endDateStr) {
        if(startDateStr == null){
            startDateStr = "2000-01-01";
        }
        if(endDateStr == null){
            endDateStr = LocalDate.now().toString();
        }
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        return ResponseEntity.ok(factApplicationService.getAllFactorCandidateByDate(startDate, endDate));
    }





}

