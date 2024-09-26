package com.api.controller;

import com.api.domain.dto.AverageByJobDto;
import com.api.domain.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity findAll() {return ResponseEntity.ok(jobService.findAll());}

    @GetMapping("/jobAverage")
    public ResponseEntity<List<AverageByJobDto>> findJobAverage(@RequestParam(required = false) Optional<Date> startDate,
                                                                @RequestParam(required = false) Optional<Date> endDate)
    {
        try{
            System.out.println(startDate + " " + endDate);
            return ResponseEntity.ok(jobService.getJobAverager(startDate, endDate));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Soccoro deus! chama odin, zeus, sla...", e);
        }

    }


}
