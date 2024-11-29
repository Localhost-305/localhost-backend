package com.api.controller;

import com.api.domain.dto.QuantityApplicationDto;
import com.api.domain.exception.InvalidMonthsException;
import com.api.domain.service.QuantityApplicationsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class QuantityApplicationsControllerTest {

    @Mock
    private QuantityApplicationsService quantityApplicationsService;

    @InjectMocks
    private QuantityApplicationsController quantityApplicationsController;

    @Test
    void testGetAmountCollectedValidMonthsWithoutProfession() {
        int months = 3;
        List<QuantityApplicationDto> expectedList = List.of(
                new QuantityApplicationDto(1, 2023, null, 150.5, 1),
                new QuantityApplicationDto(2, 2023, null, 130.0, 2)
        );

        when(quantityApplicationsService.findAll(months, null)).thenReturn(expectedList);

        List<QuantityApplicationDto> result = quantityApplicationsController.getAmountCollected(months, null);

        assertEquals(expectedList, result);
        verify(quantityApplicationsService, times(1)).findAll(months, null);
    }

    @Test
    void testGetAmountCollectedValidMonthsWithProfession() {
        int months = 6;
        String profession = "Engineer";
        List<QuantityApplicationDto> expectedList = List.of(
                new QuantityApplicationDto(1, 2023, "Engineer", 200.0, 1),
                new QuantityApplicationDto(2, 2023, "Engineer", 180.0, 2)
        );

        when(quantityApplicationsService.findAll(months, profession)).thenReturn(expectedList);

        List<QuantityApplicationDto> result = quantityApplicationsController.getAmountCollected(months, profession);

        assertEquals(expectedList, result);
        verify(quantityApplicationsService, times(1)).findAll(months, profession);
    }

    @Test
    void testGetAmountCollectedInvalidMonths() {
        int invalidMonths = 5;

        Exception exception = assertThrows(InvalidMonthsException.class,
                () -> quantityApplicationsController.getAmountCollected(invalidMonths, null));

        assertEquals("Valores fora do range. Os números permitidos são 3, 6, 9, 12 e 24.", exception.getMessage());
        verifyNoInteractions(quantityApplicationsService);
    }
}