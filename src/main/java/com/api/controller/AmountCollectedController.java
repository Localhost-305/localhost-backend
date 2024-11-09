package com.api.controller;

import com.api.domain.dto.AmountCollectedDto;
import com.api.domain.service.AmountCollectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/amount")
public class AmountCollectedController {

    @Autowired
    private AmountCollectedService amountCollectedService;

    @GetMapping ("/collected")
    public List<AmountCollectedDto> getAmountCollected (@RequestParam("months") int months ,
                                                  @RequestParam(required = false) String profession){
        return amountCollectedService.findAmountCollectedByMonths(months,profession);
    }




}
