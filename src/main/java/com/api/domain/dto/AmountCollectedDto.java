package com.api.domain.dto;


public record AmountCollectedDto(
        int year, int month, double collectedRevenue, int rank) {
}

