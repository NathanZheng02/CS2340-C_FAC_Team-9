package com.example.sprint1_main.model;

import java.util.ArrayList;
import java.util.List;

public class DestinationModel {

    private String destinationName;
    private DateModel startDate;
    private DateModel endDate;
    private int estimatedDays;
    private List<UserModel> contributingUsers;
    private List<String> notes;


    public DestinationModel(String destinationName, DateModel startDate, DateModel endDate) {
        this.destinationName = destinationName;
        this.startDate = startDate;
        this.endDate = endDate;
        //calculate estimated days based on start and end date
        this.contributingUsers = new ArrayList<>();
        this.notes = new ArrayList<>();
    }

    //user getters
    public String getDestinationName() {
        return destinationName;
    }

    public DateModel getStartDate() {
        return startDate;
    }

    public DateModel getEndDate() {
        return endDate;
    }


    //user setters
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public void setStartDate(DateModel startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(DateModel endDate) {
        this.endDate = endDate;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public List<UserModel> getContributingUsers() {
        return contributingUsers;
    }

    public void setContributingUsers(List<UserModel> contributingUsers) {
        this.contributingUsers = contributingUsers;
    }
}
