package com.api.controller;

import com.api.domain.dto.JobDto;
import com.api.domain.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JobControllerTest {

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobController jobController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllWithDates() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        ArrayList<Object> mockData = new ArrayList<>();
        mockData.add(new Object[]{"Developer", 45});
        mockData.add(new Object[]{"Tester", 30});

        when(jobService.findAll(startDate, endDate)).thenReturn(mockData);

        ResponseEntity<ArrayList<Object>> response = jobController.getAll(startDate.toString(), endDate.toString());

        assertEquals(ResponseEntity.ok(mockData), response);
        verify(jobService, times(1)).findAll(startDate, endDate);
    }

    @Test
    void testGetAllWithDefaultDates() {
        ArrayList<Object> mockResult = new ArrayList<>();
        mockResult.add("Mock Job Data");

        when(jobService.findAll(LocalDate.of(2000, 1, 1), LocalDate.now()))
                .thenReturn(mockResult);

        ResponseEntity<ArrayList<Object>> response = jobController.getAll(null, null);

        assertEquals(ResponseEntity.ok(mockResult), response);
        verify(jobService, times(1)).findAll(LocalDate.of(2000, 1, 1), LocalDate.now());
    }

    @Test
    void testGetAverageAllWithCustomDates() {
        String startDateStr = "2023-01-01";
        String endDateStr = "2023-12-31";
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        ArrayList<Object> mockResult = new ArrayList<>();
        mockResult.add("Mock Average Data");

        when(jobService.getAverageAll(startDate, endDate)).thenReturn(mockResult);

        ResponseEntity<ArrayList<Object>> response = jobController.getAverageAll(startDateStr, endDateStr);

        assertEquals(ResponseEntity.ok(mockResult), response);
        verify(jobService, times(1)).getAverageAll(startDate, endDate);
    }

    @Test
    void testGetCandidateByJobWithTitle() {
        String jobTitle = "Developer";
        ArrayList<Object> mockResult = new ArrayList<>();
        mockResult.add("Candidate Data");

        when(jobService.getCandidateByJob(jobTitle)).thenReturn(mockResult);

        ResponseEntity<ArrayList<Object>> response = jobController.getCandidateByJob(jobTitle);

        assertEquals(ResponseEntity.ok(mockResult), response);
        verify(jobService, times(1)).getCandidateByJob(jobTitle);
    }

    @Test
    void testGetAverageTimeJobs() {
        String jobTitle = "Tester";
        String startDateStr = "2023-01-01";
        String endDateStr = "2023-06-30";
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        ArrayList<Object> mockResult = new ArrayList<>();
        mockResult.add("Average Time Data");

        when(jobService.getAverageTimeJob(jobTitle, startDate, endDate)).thenReturn(mockResult);

        ResponseEntity<ArrayList<Object>> response = jobController.getAverageTimeJobs(jobTitle, startDateStr, endDateStr);

        assertEquals(ResponseEntity.ok(mockResult), response);
        verify(jobService, times(1)).getAverageTimeJob(jobTitle, startDate, endDate);
    }
}
