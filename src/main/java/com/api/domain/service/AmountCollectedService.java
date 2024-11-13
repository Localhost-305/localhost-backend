package com.api.domain.service;

import com.api.domain.dto.AmountCollectedDto;

import com.api.domain.repository.AmonuntCollectedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmountCollectedService {

    private final AmonuntCollectedRepository amountCollectedRepository;

    @Autowired
    public AmountCollectedService(AmonuntCollectedRepository amountCollectedRepository) {
        this.amountCollectedRepository = amountCollectedRepository;
    }

    public List<AmountCollectedDto> findAmountCollectedByMonths(int months, String profession) {
        List<Object[]> results = amountCollectedRepository.findAmountCollectedByMonths(months, profession);

       int monthInt =months;
        List<AmountCollectedDto> returnAverage = results.stream()
                .map(result -> new AmountCollectedDto(
                        (int) result[0],
                        (int) result[1],
                        (String) result[2],
                        ((Number) result[3]).doubleValue(),
                        ((Number) result[4]).intValue()
                ))
                .collect(Collectors.toList());

        List<AmountCollectedDto> returnAverage1 = average(returnAverage, 1, monthInt);
        List<AmountCollectedDto> returnAverage2 = average(returnAverage1, 2, monthInt);
        List<AmountCollectedDto> returnAverage3 = average(returnAverage2, 3, monthInt);
        return returnAverage3;
    }

    public List<AmountCollectedDto> average(List<AmountCollectedDto> listAvg, int plusMonth, int monthInt) {
        double sumList = 0.0;
        double sumRank = 0.0;
        int maxRank = 0;
        int elementCount = listAvg.size();

        for (AmountCollectedDto averageMonth : listAvg) {
            sumList += averageMonth.collectedRevenue();
            sumRank += averageMonth.rank();
            maxRank = Math.max(maxRank, averageMonth.rank());
        }

        double averageAll = elementCount > 0 ? sumList / elementCount : 0.0;

        if (monthInt == 3) {
            averageAll = Math.round(averageAll * (1 - 0.5) * 100.0) / 100.0;
        } else if (monthInt > 3) {
            double averageAllMonth = sumRank > 0 ? (sumList / sumRank) : 0.0;
            averageAll = (averageAllMonth + 0.5) * (1 - 0.5);
        }

        LocalDate newDate = LocalDate.now().plusMonths(plusMonth);
        AmountCollectedDto amountCollectedDto = new AmountCollectedDto(
                newDate.getYear(),
                newDate.getMonthValue(),
                listAvg.isEmpty() ? "" : listAvg.get(0).jobTitle(),
                averageAll,
                maxRank + 1
        );

        listAvg.add(amountCollectedDto);
        return listAvg;
    }
}
