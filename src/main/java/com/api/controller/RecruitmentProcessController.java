package com.api.controller;

import com.api.domain.service.CandidateService;
import com.api.domain.service.RecruitmentProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rercuitmentProcess")
public class RecruitmentProcessController {

    @Autowired
    private RecruitmentProcessService recruitmentProcessService;
}
