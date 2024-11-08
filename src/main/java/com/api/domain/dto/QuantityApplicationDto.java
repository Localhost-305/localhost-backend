package com.api.domain.dto;

public record QuantityApplicationDto(int month, int year,String jobTitle, double quantityApplications, int rank) {
    // O construtor é gerado automaticamente, não é necessário redefini-lo.

    @Override
    public String toString() {
        return "QuantityApplicationDto{" +
                "month=" + month +
                ", year=" + year +
               ", job=" +jobTitle +
                ", quantityApplications=" + quantityApplications +
                ", rank=" + rank +
                '}';
    }
}
