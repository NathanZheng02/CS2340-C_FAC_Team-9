package com.example.sprint1_main.model;

import java.io.Serializable;

public class TimeModel implements Serializable {


    private int hour;
    private int minute;

    public TimeModel() {

    }

    public TimeModel(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }



    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
