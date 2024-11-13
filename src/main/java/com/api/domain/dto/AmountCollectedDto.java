package com.api.domain.dto;


public record AmountCollectedDto(
        int year, int month,String jobTitle, double collectedRevenue, int rank) {
}

