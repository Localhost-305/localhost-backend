package com.api.domain.service;

import com.api.domain.entity.FactHiring;
import com.api.domain.repository.FactHiringRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service
public class FactHiringService {

    private final FactHiringRepository factHiringRepository;

    public FactHiringService(FactHiringRepository factHiringRepository) {
        this.factHiringRepository = factHiringRepository;
    }

    public List<Map<String, Object>> calculateTotalCostPerMonth(LocalDate startDate, LocalDate endDate) {
        Date start = java.sql.Date.valueOf(startDate);
        Date end = java.sql.Date.valueOf(endDate);

        List<FactHiring> hirings = factHiringRepository.findByHiringDateBetween(start, end);

        return hirings.stream()
                .collect(Collectors.groupingBy(
                        hiring -> {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(hiring.getHiringDate());
                            int year = cal.get(Calendar.YEAR);
                            int month = cal.get(Calendar.MONTH) + 1; 
                            return new AbstractMap.SimpleEntry<>(year, month);
                        },
                        Collectors.summingDouble(hiring -> hiring.getInitialSalary() * 0.15)
                ))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("ano", entry.getKey().getKey());
                    result.put("mes", entry.getKey().getValue());
                    result.put("somaDoCusto", entry.getValue());
                    return result;
                })
                .collect(Collectors.toList());
    }

    public double calculateRetentionRate(LocalDate startDate, LocalDate endDate) {
        List<Map<String, Object>> retentionRates = factHiringRepository.calculateRetentionRate(startDate, endDate);

        double totalRetention = retentionRates.stream()
                .mapToDouble(entry -> {
                    BigDecimal retentionValue = (BigDecimal) entry.get("average_retention_days");
                    return retentionValue != null ? retentionValue.doubleValue() : 0.0;
                })
                .average()
                .orElse(0.0);

        return totalRetention;
    }

}
