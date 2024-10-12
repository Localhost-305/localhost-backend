package com.api.controller;

import com.api.domain.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity findAll() {return ResponseEntity.ok(jobService.findAll());}

    @GetMapping("/jobAverage")
    public ResponseEntity<ArrayList<Object>> getAll(@RequestParam(required = false)  String startDateStr , @RequestParam(required = false)  String endDateStr) {
        if(startDateStr == null){
            startDateStr = "2000-01-01";
        }
        if(endDateStr == null){
            endDateStr = LocalDate.now().toString();
        }
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        return ResponseEntity.ok(jobService.findAll(startDate, endDate));
    }


    @GetMapping("/jobAverageAll")
    public ResponseEntity<ArrayList<Object>> getAverageAll(@RequestParam(required = false)  String startDateStr , @RequestParam(required = false)  String endDateStr) {

        if(startDateStr == null){
            startDateStr = "2000-01-01";
        }
        if(endDateStr == null){
            endDateStr = LocalDate.now().toString();
        }

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        return ResponseEntity.ok(jobService.getAverageAll(startDate, endDate));
    }
}
