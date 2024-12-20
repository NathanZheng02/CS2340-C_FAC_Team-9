package com.example.sprint1_main.model;

import java.io.Serializable;

public class DateModel implements Serializable {
    private int day;
    private int month;
    private int year;

    public DateModel() {

    }




    public DateModel(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year; // Format as needed
    }

}
