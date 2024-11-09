package com.example.sprint1_main.model;

import java.util.ArrayList;
import java.util.List;

public class ReservationModel {

    private String location;
    private String website;
    private List<String> reviews;
    private String time;

    public ReservationModel() {

    }

    public ReservationModel(String location, String website, String time) {
        this.location = location;
        this.website = website;
        this.reviews = new ArrayList<>();
        this.time = time;
    }
}
