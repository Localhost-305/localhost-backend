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

    public List<AmountCollectedDto> findAmountCollectedByMonths(int months, String profissao) {
        List<Object[]> results = amonuntCollectedRepository.findAmountCollectedByMonths(months,profissao);

        return results.stream()
                .map(result -> new AmountCollectedDto(
                        (int) result[0],                   // year
                        (int) result[1],
                        (String) result[2],// month
                        ((Number) result[3]).doubleValue(), // collected revenue
                        ((Number) result[4]).intValue()     // rank // rank
                ))
                .collect(Collectors.toList());
    }





}
