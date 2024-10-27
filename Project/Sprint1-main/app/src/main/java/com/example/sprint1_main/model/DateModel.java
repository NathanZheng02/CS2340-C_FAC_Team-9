package com.example.sprint1_main.model;

public class DateModel {
    private final int day;
    private final int month;
    private final int year;



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


}
