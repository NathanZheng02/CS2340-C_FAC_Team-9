package com.example.sprint1_main.model;

public class DestinationModel {

    private String destinationName;
    private String startDate;
    private String endDate;


    public DestinationModel(String destinationName, String startDate, String endDate) {
        this.destinationName = destinationName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //user getters
    public String getDestinationName() {
        return destinationName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }


    //user setters
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
