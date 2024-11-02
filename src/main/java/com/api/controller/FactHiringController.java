package com.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.domain.service.FactHiringService;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashMap;
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


@GetMapping("/retention")
public ResponseEntity<Map<String, Object>> getRetentionRate(
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
    double retentionRate = factHiringService.calculateRetentionRate(startDate, endDate);

    // Cria o objeto de resposta no formato "retentionDays: ..."
    Map<String, Object> response = new HashMap<>();
    response.put("retentionDays", retentionRate);

    return ResponseEntity.ok(response);
}


}
