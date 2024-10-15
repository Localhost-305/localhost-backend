package com.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.domain.service.FactHiringService;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hiring")
public class FactHiringController {

    private final FactHiringService factHiringService;

    public FactHiringController(FactHiringService factHiringService) {
        this.factHiringService = factHiringService;
    }

    @GetMapping("cost")
    public ResponseEntity<List<Map<String, Object>>> getHiringCost(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<Map<String, Object>> totalCost = factHiringService.calculateTotalCostPerMonth(startDate, endDate);
        return ResponseEntity.ok(totalCost);
    }
}
