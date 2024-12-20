package com.api.controller;

import com.api.domain.dto.AmountCollectedDto;
import com.api.domain.dto.QuantityApplicationDto;
import com.api.domain.exception.InvalidMonthsException;
import com.api.domain.service.QuantityApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/quantityApplications")
public class QuantityApplicationsController {

    @Autowired
    private QuantityApplicationsService quantityApplicationsService;

    @GetMapping ("/collected")
      public List<QuantityApplicationDto> getAmountCollected (@RequestParam("months") int months ,
                                                        @RequestParam(required = false) String profession){

        List<Integer> validMonths = Arrays.asList(3, 6, 9, 12, 24);

        if (!validMonths.contains(months)) {
            throw new InvalidMonthsException("Valores fora do range. Os números permitidos são 3, 6, 9, 12 e 24.");
        }
        return quantityApplicationsService.findAll(months,profession);
    }
}
