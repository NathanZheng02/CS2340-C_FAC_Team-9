package com.example.sprint1_main.model;

public class DestinationModel {
    private double duration;
    private String startDate;
    private String endDate;

    public DestinationModel() {
        duration = 0.0;
        startDate = "Unknown";
        endDate = "Unknown";
    }

    public double getDuration() {
        return duration;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}