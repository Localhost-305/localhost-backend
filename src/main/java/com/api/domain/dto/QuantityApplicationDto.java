package com.api.domain.dto;

public class QuantityApplicationDto {
    private int month;
    private int year;
    private double quantityApplications;
    private int rank; // Adicione este atributo

    @Override
    public String toString() {
        return "QuantityApplicationDto{" +
                "month=" + month +
                ", year=" + year +
                ", quantityApplications=" + quantityApplications +
                ", rank=" + rank +
                '}';
    }

    public QuantityApplicationDto(int month, int year, double quantityApplications, int rank) {
        this.month = month;
        this.year = year;
        this.quantityApplications = quantityApplications;
        this.rank = rank; // Inicializa o rank
    }

    // Getters e Setters
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getQuantityApplications() {
        return quantityApplications;
    }

    public void setQuantityApplications(double quantityApplications) {
        this.quantityApplications = quantityApplications;
    }

    public int getRank() { // Adicione este getter
        return rank;
    }

    public void setRank(int rank) { // Adicione este setter
        this.rank = rank;
    }
}
