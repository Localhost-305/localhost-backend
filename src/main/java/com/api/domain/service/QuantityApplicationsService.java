package com.api.domain.service;

import com.api.domain.dto.QuantityApplicationDto;
import com.api.domain.repository.QuantityApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuantityApplicationsService {

    @Autowired
    private QuantityApplicationsRepository quantityApplicationsRepository;

    public List<QuantityApplicationDto> findAll(int months) {
        // Garante que o número de meses esteja entre 3 e 24
        if (months < 3) months = 3;
        if (months > 24) months = 24;

        // Chama o repositório para obter os dados
        List<Object[]> results = quantityApplicationsRepository.findAllHiringDatesAndTotalHiringsByMonths(months);

        // Mapa para armazenar a soma total por mês e ano
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

        // Ordena a lista por ano e mês antes de calcular a média ponderada
        resultList.sort((a, b) -> {
            if (a.getYear() == b.getYear()) {
                return Integer.compare(a.getMonth(), b.getMonth());
            }
            return Integer.compare(a.getYear(), b.getYear());
        });

        // Gera três meses à frente da data atual
        List<QuantityApplicationDto> futureMonths = generateFutureMonths(resultList, months);

        // Adiciona os novos meses ao final da lista original
        resultList.addAll(futureMonths);

        // Ordena a lista final em ordem crescente de mês e ano
        resultList.sort((a, b) -> {
            if (a.getYear() == b.getYear()) {
                return Integer.compare(a.getMonth(), b.getMonth());
            }
            return Integer.compare(a.getYear(), b.getYear());
        });

        return resultList;
    }

    // Função para gerar três meses futuros
    private List<QuantityApplicationDto> generateFutureMonths(List<QuantityApplicationDto> originalList, int months) {
        List<QuantityApplicationDto> futureMonths = new ArrayList<>();

        // Certifique-se de que há pelo menos 'months' meses de dados para calcular a média ponderada
        if (originalList.size() < months) {
            return futureMonths; // Não há dados suficientes para gerar futuros
        }

        // Gera três meses futuros
        for (int i = 1; i <= 3; i++) {
            // Obtém os últimos 'months' meses para cálculo da média ponderada
            List<QuantityApplicationDto> sublist = originalList.subList(originalList.size() - months, originalList.size());

            // Calcula a média ponderada
            double weightedSum = 0.0;
            double totalQuantity = 0.0;

            for (QuantityApplicationDto dto : sublist) {
                weightedSum += dto.getQuantityApplications() * dto.getRank();
                totalQuantity += dto.getQuantityApplications();
            }

            double weightedAverage = weightedSum / totalQuantity; // Média ponderada pela soma de quantityApplications

            // Obtém o último mês processado para gerar o próximo mês
            QuantityApplicationDto lastMonth = futureMonths.isEmpty()
                    ? originalList.get(originalList.size() - 1)
                    : futureMonths.get(futureMonths.size() - 1);

            // Incrementa o mês e ajusta o ano, se necessário
            int newMonth = lastMonth.getMonth() + 1;
            int newYear = lastMonth.getYear();
            if (newMonth > 12) {
                newMonth = 1;
                newYear++;
            }

            // Cria o novo DTO para o próximo mês com base na média ponderada
            QuantityApplicationDto newTrend = new QuantityApplicationDto(newMonth, newYear, weightedAverage, i); // Rank crescente de 1 a 3
            futureMonths.add(newTrend);
        }

        return futureMonths;
    }
}
