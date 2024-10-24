package com.api.domain.service;

import com.api.domain.dto.QuantityApplicationDto;
import com.api.domain.repository.QuantityApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuantityApplicationsService {

    @Autowired
    private QuantityApplicationsRepository quantityApplicationsRepository;

    public List<QuantityApplicationDto> findAll(int months) {
        // Chama o repositório para obter os dados
        List<Object[]> results = quantityApplicationsRepository.findAllHiringDatesAndTotalHiringsByMonths(months);

        // Mapa para armazenar a soma total por mês
        Map<String, Double> monthlyTotals = new HashMap<>();

        // Processa os resultados para somar as aplicações por mês
        for (Object[] result : results) {
            int month = (int) result[0];  // O mês
            int year = (int) result[1];   // O ano
            double totalHirings = ((Number) result[2]).doubleValue(); // Total de contratações

            // Cria uma chave única para o mês e ano
            String key = month + "-" + year;
            monthlyTotals.put(key, monthlyTotals.getOrDefault(key, 0.0) + totalHirings);
        }

        // Lista para armazenar o resultado formatado
        List<QuantityApplicationDto> resultList = new ArrayList<>();

        // Preenche a lista com os dados agregados e calcula o ranking
        AtomicInteger rank = new AtomicInteger(1); // Inicia o ranking
        monthlyTotals.forEach((key, total) -> {
            String[] parts = key.split("-");
            int month = Integer.parseInt(parts[0]); // Obtém o mês
            int year = Integer.parseInt(parts[1]);   // Obtém o ano

            // Adiciona ao resultado usando o DTO, incluindo o rank
            QuantityApplicationDto monthlyData = new QuantityApplicationDto(month, year, total, rank.getAndIncrement());
            resultList.add(monthlyData);
        });

        return resultList;
    }
}
