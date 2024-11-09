package com.example.sprint1_main.model;

public class LodgingModel {

    private String checkInTime;
    private String checkOutTime;
    private int numRooms;
    private String roomType;

    public LodgingModel() {

    }

    public LodgingModel(String checkInTime, String checkOutTime, int numRooms, String roomType) {
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.numRooms = numRooms;
        this.roomType = roomType;
    }
}
