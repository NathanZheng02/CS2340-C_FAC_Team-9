package com.example.sprint1_main.model;

public class LodgingModel {

    private DateModel checkInTime;
    private DateModel checkOutTime;
    private int numRooms;
    private String roomType;
    private String location;

    public LodgingModel() {

    }

    public LodgingModel(DateModel checkInTime, DateModel checkOutTime, int numRooms, String roomType, String location) {
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.numRooms = numRooms;
        this.roomType = roomType;
        this.location = location;
    }
}
