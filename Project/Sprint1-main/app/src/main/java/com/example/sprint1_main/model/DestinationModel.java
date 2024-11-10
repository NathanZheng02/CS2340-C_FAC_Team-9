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
    private List<ReservationModel> reservations;
    private List<LodgingModel> lodgings;


    public DestinationModel() {

    }



    public DestinationModel(String destinationName, DateModel startDate, DateModel endDate) {
        this.destinationName = destinationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedDays = getDaysBetween(startDate, endDate);
        this.contributingUsers = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.lodgings = new ArrayList<>();
    }

    public int getDaysInMonth(int month) {
        if (month == 1) {
            return 31;
        } else if (month == 2) {
            return 28;
        } else if (month == 3) {
            return 31;
        } else if (month == 4) {
            return 30;
        } else if (month == 5) {
            return 31;
        } else if (month == 6) {
            return 30;
        } else if (month == 7) {
            return 31;
        } else if (month == 8) {
            return 31;
        } else if (month == 9) {
            return 30;
        } else if (month == 10) {
            return 31;
        } else if (month == 11) {
            return 30;
        } else if (month == 12) {
            return 31;
        }
        return 0;
    }

    public int getDaysBetween(DateModel startDate, DateModel endDate) {
        int daysBetween = 0;

        int yearsBetween = endDate.getYear() - startDate.getYear();
        daysBetween += (yearsBetween * 365);

        for (int i = startDate.getMonth(); i < endDate.getMonth(); i++) {
            int daysInMonth = getDaysInMonth(i);
            daysBetween += daysInMonth;
        }

        daysBetween += endDate.getDay() - startDate.getDay();

        return daysBetween;
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

    public int getEstimatedDays() {
        return estimatedDays;
    }

    public void setEstimatedDays(int estimatedDays) {
        this.estimatedDays = estimatedDays;
    }

    // reservations getter and setter
    public List<ReservationModel> getReservations() { return reservations; }

    public void setReservations(List<ReservationModel> reservations) {
        this.reservations = reservations;
    }

    public List<LodgingModel> getLodgings() {
        return lodgings;
    }

    public void setLodgings(List<LodgingModel> lodgings) {
        this.lodgings = lodgings;
    }
}
