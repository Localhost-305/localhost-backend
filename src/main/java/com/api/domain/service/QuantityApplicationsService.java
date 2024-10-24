package com.api.domain.service;


import com.api.domain.repository.QuantityApplicationsRepository;
import com.api.domain.util.MapObjectList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class QuantityApplicationsService {

    @Autowired
    private QuantityApplicationsRepository quantityApplicationsRepository;


    public List<Object[]> findAll(int months) {
        List<Object[]> results = quantityApplicationsRepository.findAllCandidatesAndHiringDatesByMonths(months);
        return results;
    }

}
