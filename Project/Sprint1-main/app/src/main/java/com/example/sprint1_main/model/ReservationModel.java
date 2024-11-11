package com.example.sprint1_main.model;

import java.util.ArrayList;
import java.util.List;

public class ReservationModel implements Sortable {

    private String location;
    private String website;
    private List<String> reviews;
    private DateModel date;
    private TimeModel time;

    public ReservationModel() {

    }

    public ReservationModel(String location, String website, DateModel date, TimeModel time) {
        this.location = location;
        this.website = website;
        this.reviews = new ArrayList<>();
        this.date = date;
        this.time = time;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public com.example.sprint1_main.model.TimeModel getTime() {
        return time;
    }

    public void setTime(com.example.sprint1_main.model.TimeModel time) {
        this.time = time;
    }

    public DateModel getDate() {
        return date;
    }

    public void setDate(DateModel date) {
        this.date = date;
    }
}
