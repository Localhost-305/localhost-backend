package com.api.domain.service;


import com.api.domain.repository.QuantityApplicationsRepository;
import com.api.domain.util.MapObjectList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class QuantityApplicationsService {

    @Autowired
    private QuantityApplicationsRepository quantityApplicationsRepository;


//    public List<Object[]> findAll(int months) {
//        List<Object[]> results = quantityApplicationsRepository.findAllCandidatesAndHiringDatesByMonths(months);
//        return results;
//    }

    public List<List<Object>> findAll(int months) {
        // Chama o repositório para obter os dados
        List<Object[]> results = quantityApplicationsRepository.findAllHiringDatesAndTotalHiringsByMonths(months);

        // Lista para armazenar o resultado formatado
        List<List<Object>> resultList = new ArrayList<>();

        for (Object[] result : results) {
            // Adiciona os resultados na lista
            List<Object> resultData = new ArrayList<>();
            resultData.add(result[0]); // hiring_date
            resultData.add(result[1]); // total_hirings

            resultList.add(resultData);
        }

        return resultList;
    }

}
