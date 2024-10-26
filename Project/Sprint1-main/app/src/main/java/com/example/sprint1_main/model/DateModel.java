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

    //methods need to be moved to viewmodel

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

        for (int i = startDate.month; i < endDate.month; i++) {
            int daysInMonth = getDaysInMonth(i);
            daysBetween += daysInMonth;
        }

        daysBetween += endDate.day - startDate.day;

        return daysBetween;
    }
}
