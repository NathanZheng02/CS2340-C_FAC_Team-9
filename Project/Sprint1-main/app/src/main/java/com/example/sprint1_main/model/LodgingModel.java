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

    public DateModel getCheckInTime() {
        return checkInTime;
    }

    public DateModel getCheckOutTime() {
        return checkOutTime;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getLocation() {
        return location;
    }

    public void setCheckInTime(DateModel checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(DateModel checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
