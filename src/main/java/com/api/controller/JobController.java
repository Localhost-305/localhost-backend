package com.api.controller;

import com.api.domain.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@CrossOrigin(origins = "*")
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


    @GetMapping("/getCandidateByJob")
    public ResponseEntity<ArrayList<Object>> getCandidateByJob(@RequestParam(required = false) String jobTitle){
        return ResponseEntity.ok(jobService.getCandidateByJob(jobTitle));
    }

    @GetMapping("/jobAverageOne")
    public ResponseEntity<ArrayList<Object>> getAverageTimeJobs(@RequestParam(required = false)  String jobTitle,
                                                                @RequestParam(required = false)  String startDateStr ,
                                                                @RequestParam(required = false)  String endDateStr
                                                           ) {
        if(startDateStr == null){
            startDateStr = "2000-01-01";
        }
        if(endDateStr == null){
            endDateStr = LocalDate.now().toString();
        }
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        System.out.println(jobTitle);
        System.out.println(startDate);
        System.out.println(endDate);

        return ResponseEntity.ok(jobService.getAverageTimeJob(jobTitle, startDate, endDate));
    }

}
