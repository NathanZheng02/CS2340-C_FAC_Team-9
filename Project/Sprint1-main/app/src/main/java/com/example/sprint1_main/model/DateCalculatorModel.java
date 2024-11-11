package com.example.sprint1_main.model;

public class DateCalculatorModel {

    public DateCalculatorModel() {

    }

    public boolean dateBefore(DateModel firstDate, DateModel secondDate) {
        if (firstDate.getYear() < secondDate.getYear()) {
            return true;
        } else if (firstDate.getMonth() < secondDate.getMonth()) {
            return true;
        } else if (firstDate.getDay() < secondDate.getDay()) {
            return true;
        }
        return false;
    }

    public boolean timeBefore(TimeModel firstTime, TimeModel secondTime) {
        if (firstTime.getHour() < secondTime.getHour()) {
            return true;
        } else if (firstTime.getMinute() < secondTime.getMinute()) {
            return true;
        }
        return false;
    }

    public int getDuration(DateModel startDate, DateModel endDate) {
        int duration = 0;

        int yearsBetween = endDate.getYear() - startDate.getYear();
        duration += (yearsBetween * 365);

        for (int i = startDate.getMonth(); i < endDate.getMonth(); i++) {
            int daysInMonth = getDaysInMonth(i);
            duration += daysInMonth;
        }

        duration += endDate.getDay() - startDate.getDay();

        return duration;
    }

    public DateModel getEndDate(DateModel startDate, int duration) {
        int endYear = startDate.getYear();

        while (duration >= 365) {
            endYear += 1;
            duration -= 365;
        }

        int endMonth = startDate.getMonth();

        while (duration >= getDaysInMonth(endMonth)) {
            if (endMonth == 12) {
                endMonth = 1;
                endYear += 1;
            }
            else {
                endMonth += 1;
            }
            duration -= getDaysInMonth(endMonth);
        }

        int endDay = startDate.getDay();

        while (duration > 0) {
            if (endDay == getDaysInMonth(endMonth)) {
                endDay = 1;
                if (endMonth == 12) {
                    endMonth = 1;
                    endYear += 1;
                }
                else {
                    endMonth += 1;
                }
            }
            else {
                endDay += 1;
            }
            duration -= 1;
        }


        return new DateModel(endMonth, endDay, endYear);
    }

    public DateModel getStartDate(DateModel endDate, int duration) {

        int startYear = endDate.getYear();

        while (duration >= 365) {
            startYear -= 1;
            duration -= 365;
        }

        int startMonth = endDate.getMonth();

        while (duration >= getDaysInMonth(startMonth)) {
            if (startMonth == 1) {
                startMonth = 12;
                startYear -= 1;
            }
            else {
                startMonth -= 1;
            }
            duration -= getDaysInMonth(startMonth);
        }

        int startDay = endDate.getDay();

        while (duration > 0) {
            if (startDay == getDaysInMonth(startMonth)) {

                if (startMonth == 1) {
                    startMonth = 12;
                    startYear -= 1;
                }
                else {
                    startMonth -= 1;
                }
                startDay = getDaysInMonth(startMonth);
            }
            else {
                startDay -= 1;
            }
            duration -= 1;
        }

        return new DateModel(startMonth, startDay, startYear);

    }


    private int getDaysInMonth(int month) {
        if (month == 1) {
            return 31;
        } else if (month == 2) {
            return 28;
        } else if (month == 3) {
            return 31;
        } else if (month == 4) {
            return 30;
        } else if (month == 5) {
            return 31;
        } else if (month == 6) {
            return 30;
        } else if (month == 7) {
            return 31;
        } else if (month == 8) {
            return 31;
        } else if (month == 9) {
            return 30;
        } else if (month == 10) {
            return 31;
        } else if (month == 11) {
            return 30;
        } else if (month == 12) {
            return 31;
        }
        return 0;
    }
}
