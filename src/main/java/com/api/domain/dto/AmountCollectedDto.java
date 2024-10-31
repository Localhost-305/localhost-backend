package com.api.domain.dto;


public record AmountCollectedDto(
        String period,
        double collectedRevenue,
        int rank) {
}

