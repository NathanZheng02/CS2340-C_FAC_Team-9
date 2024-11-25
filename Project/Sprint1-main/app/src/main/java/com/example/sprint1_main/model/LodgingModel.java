package com.example.sprint1_main.model;

import java.io.Serializable;

public class LodgingModel implements Sortable, Serializable {



    private DateModel checkInTime;
    private DateModel checkOutTime;
    private int numRooms;
    private String roomType;
    private String location;

    public LodgingModel() {

    }

    public LodgingModel(DateModel checkInTime, DateModel checkOutTime,
                        int numRooms, String roomType, String location) {
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.numRooms = numRooms;
        this.roomType = roomType;
        this.location = location;
    }

    public DateModel getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(DateModel checkInTime) {
        this.checkInTime = checkInTime;
    }

    public DateModel getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(DateModel checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
