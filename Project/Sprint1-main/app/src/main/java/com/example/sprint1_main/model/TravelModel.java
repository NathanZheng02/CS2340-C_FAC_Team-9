package com.example.sprint1_main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TravelModel implements Serializable {



    private List<String> users;
    private DateModel startDate;
    private DateModel endDate;
    private int duration;
    private List<DestinationModel> destinations;
    private List<String> transportation;
    private List<String> notes;
    private int postNum;

    public TravelModel() {

    }

    public TravelModel(UserModel user, DateModel startDate, DateModel endDate) {
        this.users = new ArrayList<>();
        this.users.add(user.getUsername());
        this.startDate = startDate;
        this.endDate = endDate;


        if (startDate != null && endDate != null) {
            DateCalculatorModel calculator = new DateCalculatorModel();
            this.duration = calculator.getDuration(startDate, endDate);
        }


        this.destinations = new ArrayList<>();
        this.transportation = new ArrayList<>();
        this.notes = new ArrayList<>();

        TravelDatabaseModel travelDatabase = TravelDatabaseModel.getInstance();
        this.postNum = travelDatabase.getTravels().size();
    }



    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public DateModel getStartDate() {
        return startDate;
    }

    public void setStartDate(DateModel startDate) {
        this.startDate = startDate;
        if (this.startDate != null && this.endDate != null) {
            DateCalculatorModel calculator = new DateCalculatorModel();
            this.duration = calculator.getDuration(startDate, endDate);
        }

    }

    public DateModel getEndDate() {
        return endDate;
    }

    public void setEndDate(DateModel endDate) {
        this.endDate = endDate;
        if (this.startDate != null && this.endDate != null) {
            DateCalculatorModel calculator = new DateCalculatorModel();
            this.duration = calculator.getDuration(startDate, endDate);
        }
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<DestinationModel> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationModel> destinations) {
        this.destinations = destinations;
    }

    public List<String> getTransportation() {
        return transportation;
    }

    public void setTransportation(List<String> transportation) {
        this.transportation = transportation;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }
}
