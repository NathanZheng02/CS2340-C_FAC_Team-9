package com.example.sprint1_main.model;

import java.util.ArrayList;
import java.util.List;

public class DestinationModel {

    private String destinationName;
    private String startDate;
    private String endDate;
    private int estimatedDays;



    private List<UserModel> contributingUsers;
    private List<String> notes;




    public DestinationModel(String destinationName, String startDate, String endDate) {
        this.destinationName = destinationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contributingUsers = new ArrayList<>();
        this.notes = new ArrayList<>();
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
