package com.api.controller;


import com.api.domain.dto.QuantityApplicationDto;
import com.api.domain.exception.InvalidMonthsException;
import com.api.domain.service.QuantityApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping ("/quantityApplications")
public class QuantityApplicationsController {
//http://localhost:9090/quantityApplications/candidates/3
    @Autowired
    private QuantityApplicationsService quantityApplicationsService;


    @GetMapping("/months/{months}")
    public List<QuantityApplicationDto> getHiringSummary(@PathVariable int months) {

        List<Integer> validMonths = Arrays.asList(3,6,9,12,24);

        if (!validMonths.contains(months)){
            throw  new InvalidMonthsException("Valores Fora do Range, os numeros permitodos são 3,6,9,12 e24");
        }
        return quantityApplicationsService.findAll(months);
    }
}
