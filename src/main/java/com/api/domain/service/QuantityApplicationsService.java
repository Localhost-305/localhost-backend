package com.api.domain.service;

import com.api.domain.dto.QuantityApplicationDto;
import com.api.domain.repository.QuantityApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FilterOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuantityApplicationsService {

    @Autowired
    private QuantityApplicationsRepository quantityApplicationsRepository;

    public List<QuantityApplicationDto> findAll(int months) {
        // Chama o repositório para obter os dados
        List<Object[]> results = quantityApplicationsRepository.findApplicationsByMonths(months);

        int monthInt = months;

        // Converte os dados para uma lista de QuantityApplicationDto
        List<QuantityApplicationDto> returnAverage = results.stream()
                .map(result -> new QuantityApplicationDto(
                        (int) result[0],                // year
                        (int) result[1],                // month
                        ((Number) result[2]).doubleValue(), // total applications
                        ((Number) result[3]).intValue()     // rank
                ))
                .collect(Collectors.toList());

        List<QuantityApplicationDto> returnAverage1 = average(returnAverage, 1, monthInt);
        List<QuantityApplicationDto> returnAverage2 = average(returnAverage1, 2, monthInt);
        List<QuantityApplicationDto> returnAverage3 = average(returnAverage2, 3, monthInt);
        return returnAverage3;
    }


    public List<QuantityApplicationDto> average(List<QuantityApplicationDto> listAvg, int plusMonth, int monthInt) {

        Double sumList = 0.0;
        Double sumRank = 0.0;
        int maxRank = 0;
        int elementCount = listAvg.size();

        for (QuantityApplicationDto averageMouth : listAvg) {
            //sumList += averageMouth.getQuantityApplications() * averageMouth.getRank();
            sumList += averageMouth.getQuantityApplications();
            sumRank += averageMouth.getRank();
            maxRank = averageMouth.getRank();

        }

        maxRank++;


        Double averageAll = (sumList / elementCount);
        if (monthInt == 3) {
            averageAll = Math.round(averageAll * (1 - 0.5) * 100.0) / 100.0;
        } else if (monthInt > 3) {
            Double averageAllMonth = sumList / sumRank;
            averageAllMonth = (averageAllMonth + 0.5) * (1 - 0.5);
            averageAll = Math.round(averageAll * (1 - 0.5) * 100.0) / 100.0;
        }


        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);
        LocalDate newDate = currentDate.plusMonths(plusMonth);

        QuantityApplicationDto quantityApplicationDto = new QuantityApplicationDto(newDate.getMonthValue(), newDate.getYear(), averageAll, maxRank);
        listAvg.add(quantityApplicationDto);

        return listAvg;
    }
}
