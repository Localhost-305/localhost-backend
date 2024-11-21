package com.api.controller;

import com.api.domain.dto.AmountCollectedDto;
import com.api.domain.service.AmountCollectedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AmountCollectedControllerTest {

    @InjectMocks
    private AmountCollectedController amountCollectedController;

    @Mock
    private AmountCollectedService amountCollectedService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAmountCollectedWithoutProfession() {
        int months = 6;

        List<AmountCollectedDto> mockData = List.of(
                new AmountCollectedDto(2023, 6, "Developer", 10000.0, 1),
                new AmountCollectedDto(2023, 6, "Tester", 8000.0, 2)
        );

        when(amountCollectedService.findAmountCollectedByMonths(months, null)).thenReturn(mockData);

        List<AmountCollectedDto> response = amountCollectedController.getAmountCollected(months, null);

        assertEquals(mockData, response);
        verify(amountCollectedService, times(1)).findAmountCollectedByMonths(months, null);
    }

    @Test
    void testGetAmountCollectedWithProfession() {
        int months = 6;
        String profession = "Developer";

        List<AmountCollectedDto> mockData = List.of(
                new AmountCollectedDto(2023, 6, "Developer", 15000.0, 1)
        );

        when(amountCollectedService.findAmountCollectedByMonths(months, profession)).thenReturn(mockData);

        List<AmountCollectedDto> response = amountCollectedController.getAmountCollected(months, profession);

        assertEquals(mockData, response);
        verify(amountCollectedService, times(1)).findAmountCollectedByMonths(months, profession);
    }

    @Test
    void testGetAmountCollectedInvalidMonths() {
        int invalidMonths = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            amountCollectedController.getAmountCollected(invalidMonths, null);
        });

        verifyNoInteractions(amountCollectedService);
    }

    @Test
    void testGetAmountCollectedEmptyResponse() {
        int months = 6;
        String profession = "Tester";

        List<AmountCollectedDto> emptyList = List.of();

        when(amountCollectedService.findAmountCollectedByMonths(months, profession)).thenReturn(emptyList);

        List<AmountCollectedDto> response = amountCollectedController.getAmountCollected(months, profession);

        assertTrue(response.isEmpty());
        verify(amountCollectedService, times(1)).findAmountCollectedByMonths(months, profession);
    }

    @Test
    void testGetAmountCollectedServiceThrowsException() {
        int months = 6;
        String profession = "Developer";

        when(amountCollectedService.findAmountCollectedByMonths(months, profession))
                .thenThrow(new RuntimeException("Service error"));

        assertThrows(RuntimeException.class, () -> {
            amountCollectedController.getAmountCollected(months, profession);
        });

        verify(amountCollectedService, times(1)).findAmountCollectedByMonths(months, profession);
    }
}
