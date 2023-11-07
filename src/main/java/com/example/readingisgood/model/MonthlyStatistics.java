package com.example.readingisgood.model;

public class MonthlyStatistics {
    private long totalOrderCount;
    private double totalOrderAmount;
    private long totalBookCount;

    public MonthlyStatistics() {
    }

    public MonthlyStatistics(long totalOrderCount, double totalOrderAmount, long totalBookCount) {
        this.totalOrderCount = totalOrderCount;
        this.totalOrderAmount = totalOrderAmount;
        this.totalBookCount = totalBookCount;
    }

    public long getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(long totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public double getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(double totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public long getTotalBookCount() {
        return totalBookCount;
    }

    public void setTotalBookCount(long totalBookCount) {
        this.totalBookCount = totalBookCount;
    }
}
