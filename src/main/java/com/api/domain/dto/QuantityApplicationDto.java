package com.api.domain.dto;

public class QuantityApplicationDto {

    private int month;
    private int year;
    private double quantityApplications;

    public QuantityApplicationDto(int month, int year, double quantityApplications) {
        this.month = month;
        this.year = year;
        this.quantityApplications = quantityApplications;
    }

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
}
