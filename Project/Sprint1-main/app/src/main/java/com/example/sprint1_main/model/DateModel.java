package com.example.sprint1_main.model;

public class DateModel {
    private int day;
    private int month;
    private int year;

    public DateModel(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDaysInMonth(int month) {
        if (month == 1) {
            return 31;
        }
        else if (month == 2) {
            return 28;
        }
        else if (month == 3) {
            return 31;
        }
        else if (month == 4) {
            return 30;
        }
        else if (month == 5) {
            return 31;
        }
        else if (month == 6) {
            return 30;
        }
        else if (month == 7) {
            return 31;
        }
        else if (month == 8) {
            return 31;
        }
        else if (month == 9) {
            return 30;
        }
        else if (month == 10) {
            return 31;
        }
        else if (month == 11) {
            return 30;
        }
        else if (month == 12) {
            return 31;
        }
        return 0;
    }

    public int getDaysBetween(DateModel startDate, DateModel endDate) {
        int daysBetween = 0;

        int yearsBetween = endDate.year - startDate.year;
        daysBetween += (yearsBetween * 365);

        //figure out what goes here. possibly use dictionary?
        daysBetween += (yearsBetween * 365);
    }
}
