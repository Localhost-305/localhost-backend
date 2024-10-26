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


        // Converte os dados para uma lista de QuantityApplicationDto
        List<QuantityApplicationDto> retornoMedia = results.stream()
                .map(result -> new QuantityApplicationDto(
                        (int) result[0],                // year
                        (int) result[1],                // month
                        ((Number) result[2]).doubleValue(), // total applications
                        ((Number) result[3]).intValue()     // rank
                ))
                .collect(Collectors.toList());

        List<QuantityApplicationDto> retornoMedia1 = average(retornoMedia,1);
        List<QuantityApplicationDto> retornoMedia2 = average(retornoMedia1,2);
        List<QuantityApplicationDto> retornoMedia3 = average(retornoMedia2,3);

        System.out.println(retornoMedia1.size());
        System.out.println(retornoMedia2.size());
        System.out.println(retornoMedia3.size());


        return  retornoMedia3;
    }


    public  List <QuantityApplicationDto> average(List<QuantityApplicationDto> listAvg, int plusMonth){

        Double sumList = 0.0;
        Double sumRank = 0.0;
        int    maxRank =0;

        for (QuantityApplicationDto averageMouth:listAvg){
            sumList += averageMouth.getQuantityApplications() * averageMouth.getRank();
            sumRank += averageMouth.getRank();

            System.out.println(averageMouth.getQuantityApplications());

            if (maxRank < averageMouth.getRank()){
                maxRank = averageMouth.getRank() ;
            }
        }

        System.out.println();

        Double averageAll = sumList / sumRank;

        maxRank++;
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);
        LocalDate newDate = currentDate.plusMonths(plusMonth);

        QuantityApplicationDto quantityApplicationDto = new QuantityApplicationDto(newDate.getMonthValue(),newDate.getYear(),averageAll,maxRank);

//        System.out.println(quantityApplicationDto.getQuantityApplications());

        listAvg.add(quantityApplicationDto);

        return listAvg;
    }
}
