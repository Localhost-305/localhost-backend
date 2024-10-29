package com.api.controller;

import com.api.domain.service.AmountCollectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amount")
public class AmountCollectedController {

    @Autowired
    private AmountCollectedService amountCollectedService;

    @GetMapping ("/allAlmount")
    public void getAllAmount (){
        return;
    }
}
