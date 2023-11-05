package com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects;


public record CustomerPerformanceMetricSet(
        Integer totalCompletedBars,
        Integer totalOffers) {
    public CustomerPerformanceMetricSet(){
        this(0,0);
    }

    public CustomerPerformanceMetricSet incrementTotalCompletedBars() {
        return new CustomerPerformanceMetricSet(totalCompletedBars + 1, totalOffers);
    }

    public CustomerPerformanceMetricSet incrementTotalOffers() {
        return new CustomerPerformanceMetricSet(totalCompletedBars, totalOffers + 1);
    }

    public CustomerPerformanceMetricSet {
        if (totalCompletedBars < 0) {
            throw new IllegalArgumentException("Total completed bars cannot be negative");
        }

        if (totalOffers < 0) {
            throw new IllegalArgumentException("Total offers cannot be negative");
        }
    }
}
