package com.api.controller;

import com.api.domain.dto.AmountCollectedDto;
import com.api.domain.service.AmountCollectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/amount")
public class AmountCollectedController {

    @Autowired
    private AmountCollectedService amountCollectedService;

    @GetMapping ("/collected")
    public List<AmountCollectedDto> getAllAmount (@RequestParam("months") int months ,
                                                  @RequestParam(required = false) String profissao){
        return amountCollectedService.findAmountCollectedByMonths(months,profissao);
    }




}
