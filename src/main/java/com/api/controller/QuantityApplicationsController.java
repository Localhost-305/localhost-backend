package com.api.controller;


import com.api.domain.service.QuantityApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/quantityApplications")
public class QuantityApplicationsController {
//http://localhost:9090/quantityApplications/candidates/3
    @Autowired
    private QuantityApplicationsService quantityApplicationsService;

    @GetMapping("/candidates/{months}")
    public List<Object[]> getCandidates(@PathVariable int months) {
        return quantityApplicationsService.findAll(months);
    }


}
