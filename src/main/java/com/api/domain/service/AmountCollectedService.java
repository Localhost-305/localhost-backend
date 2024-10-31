package com.api.domain.service;

import com.api.domain.dto.AmountCollectedDto;
import com.api.domain.repository.AmonuntCollectedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmountCollectedService {


    private final  AmonuntCollectedRepository amonuntCollectedRepository;

    @Autowired
    //Metodo construtor
    public AmountCollectedService(AmonuntCollectedRepository amonuntCollectedRepository){
        this.amonuntCollectedRepository = amonuntCollectedRepository;
    }

    public List<AmountCollectedDto> findAmountCollectedByMonths(int months) {
        List<Object[]> results = amonuntCollectedRepository.findAmountCollectedByMonths(months);

        return results.stream()
                .map(result -> new AmountCollectedDto(
                        (String) result[0],                   // period (YYYY-MM)
                        ((Number) result[1]).doubleValue(),   // collected revenue
                        ((Number) result[2]).intValue()       // rank
                ))
                .collect(Collectors.toList());
    }





}
