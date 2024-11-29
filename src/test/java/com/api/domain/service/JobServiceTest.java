package com.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.api.domain.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class JobServiceTest {
    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAverageTimeJob() {
        String jobTitle = "Desenvolvedor";
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);

        List<Object[]> mockResult = new ArrayList<>();
        mockResult.add(new Object[]{"Desenvolvedor", 30});

        when(jobRepository.getAverageTimeJob(jobTitle, startDate, endDate)).thenReturn(mockResult);
        ArrayList<Object> result = jobService.getAverageTimeJob(jobTitle, startDate, endDate);

        Map<String, Object> resultMap = (Map<String, Object>) result.get(0);

        assertEquals("Desenvolvedor", resultMap.get("JobTitle"));
        assertEquals("30", resultMap.get("AverageTime"));

        verify(jobRepository).getAverageTimeJob(jobTitle, startDate, endDate);
    }

    @Test
    void testGetAverageTimeJobWithDifferentJobTitle() {
        String jobTitle = "Gerente";
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        List<Object[]> mockResult = new ArrayList<>();
        mockResult.add(new Object[]{"Gerente", 45});

        when(jobRepository.getAverageTimeJob(jobTitle, startDate, endDate)).thenReturn(mockResult);

        ArrayList<Object> result = jobService.getAverageTimeJob(jobTitle, startDate, endDate);

        Map<String, Object> resultMap = (Map<String, Object>) result.get(0);

        assertEquals("Gerente", resultMap.get("JobTitle"));
        assertEquals("45", resultMap.get("AverageTime"));

        verify(jobRepository).getAverageTimeJob(jobTitle, startDate, endDate);
    }

    @Test
    void testGetAverageTimeJobWithNoData() {
        String jobTitle = "Analista";
        LocalDate startDate = LocalDate.of(2021, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        List<Object[]> mockResult = new ArrayList<>();

        when(jobRepository.getAverageTimeJob(jobTitle, startDate, endDate)).thenReturn(mockResult);

        ArrayList<Object> result = jobService.getAverageTimeJob(jobTitle, startDate, endDate);

        assertEquals(0, result.size());

        verify(jobRepository).getAverageTimeJob(jobTitle, startDate, endDate);
    }
}
