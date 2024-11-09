package com.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.domain.service.FactHiringService;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
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


    @GetMapping("/retention")
    public ResponseEntity<List<Map<String, Object>>> getRetentionRate(
            @RequestParam(required = false) String startDateStr,
            @RequestParam(required = false) String endDateStr) {

        if (startDateStr == null) {
            startDateStr = "2000-01-01"; // data padrão para início
        }
        if (endDateStr == null) {
            endDateStr = LocalDate.now().toString(); // data padrão para fim
        }

        // Converte as strings para LocalDate
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        // Chama o serviço para calcular a taxa de retenção
        List<Map<String, Object>> retentionRate = factHiringService.calculateRetentionRate(startDate, endDate);
        return ResponseEntity.ok(retentionRate);
    }


}
