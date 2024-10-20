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
    private JobRepository jobRepository; // Mock do repositório

    @InjectMocks
    private JobService jobService; // Serviço que estamos testando

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Inicializa os mocks (para versões mais antigas do Mockito)
    }

    @Test
    void testGetAverageTimeJob() {
        //Teste 01
        // Dados de entrada
        String jobTitle = "Desenvolvedor";
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);

        // Resultado esperado do repositório
        List<Object[]> mockResult = new ArrayList<>();
        mockResult.add(new Object[]{"Desenvolvedor", 30});

        // Configurando o mock para retornar o resultado simulado
        when(jobRepository.getAverageTimeJob(jobTitle, startDate, endDate)).thenReturn(mockResult);
        ArrayList<Object> result = jobService.getAverageTimeJob(jobTitle, startDate, endDate);

        // Convertendo o resultado para Map para facilitar o acesso
        Map<String, Object> resultMap = (Map<String, Object>) result.get(0);

        // Verificando se o resultado é o esperado
        assertEquals("Desenvolvedor", resultMap.get("JobTitle"));
        assertEquals("30", resultMap.get("AverageTime"));

        // Verificando se o repositório foi chamado corretamente
        verify(jobRepository).getAverageTimeJob(jobTitle, startDate, endDate);
    }

    @Test
    void testGetAverageTimeJobWithDifferentJobTitle() {
        //Teste 02
        // Dados de entrada
        String jobTitle = "Gerente";
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        // Resultado esperado do repositório
        List<Object[]> mockResult = new ArrayList<>();
        mockResult.add(new Object[]{"Gerente", 45});

        // Configurando o mock para retornar o resultado simulado
        when(jobRepository.getAverageTimeJob(jobTitle, startDate, endDate)).thenReturn(mockResult);

        ArrayList<Object> result = jobService.getAverageTimeJob(jobTitle, startDate, endDate);

        // Convertendo o resultado para Map para facilitar o acesso
        Map<String, Object> resultMap = (Map<String, Object>) result.get(0);

        // Verificando se o resultado é o esperado
        assertEquals("Gerente", resultMap.get("JobTitle"));
        assertEquals("45", resultMap.get("AverageTime"));

        // Verificando se o repositório foi chamado corretamente
        verify(jobRepository).getAverageTimeJob(jobTitle, startDate, endDate);
    }

    @Test
    void testGetAverageTimeJobWithNoData() {
        //Teste 03
        // Dados de entrada
        String jobTitle = "Analista";
        LocalDate startDate = LocalDate.of(2021, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        // Configurando o mock para retornar uma lista vazia (nenhum dado encontrado)
        List<Object[]> mockResult = new ArrayList<>();

        // Configurando o mock para retornar o resultado simulado
        when(jobRepository.getAverageTimeJob(jobTitle, startDate, endDate)).thenReturn(mockResult);

        ArrayList<Object> result = jobService.getAverageTimeJob(jobTitle, startDate, endDate);

        // Verificando se a lista de resultados está vazia
        assertEquals(0, result.size());

        // Verificando se o repositório foi chamado corretamente
        verify(jobRepository).getAverageTimeJob(jobTitle, startDate, endDate);
    }
}
