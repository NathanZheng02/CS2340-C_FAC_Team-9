package com.example.sprint1_main.viewmodel;

import static java.lang.Integer.parseInt;

import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DestinationViewModel extends ViewModel {
    public DestinationViewModel() {

    }
    public static String calculateDuration(String start, String end) {
        int finalMonth = parseInt(end.substring(0, 2));
        int startMonth = parseInt(start.substring(0, 2));
        int startDay = parseInt(start.substring(3, 5));
        int endDay = parseInt(end.substring(3, 5));
        int totalDays = 0;
        if (startMonth == finalMonth) {
            totalDays = endDay - startDay;
        } else {
            int temp = startMonth;
            while (temp != finalMonth) {
                if (temp == 12) {
                    temp = 1;
                } else {
                    temp++;
                }
                totalDays += getDaysInMonth(temp);
            }
            totalDays += getDaysInMonth(startMonth) - startDay - getDaysInMonth(finalMonth) + endDay;
        }
        totalDays += parseInt(end.substring(6)) - parseInt(end.substring(6));
        return totalDays + "";
    }
    public static String calculateStartDate(String duration, String end) {
        int newTime = parseInt(duration);
        int[] mdy = new int[3];
        int temp = parseInt(end.substring(0, 2));
        while (newTime > 0) {
            if (newTime / 365 != 0) {
                mdy[2]++;
                newTime = newTime - 365;
            } else if (newTime / getDaysInMonth(temp) != 0) {
                mdy[0]++;
                newTime = newTime - getDaysInMonth(temp);
                if (temp == 1) {
                    temp = 12;
                } else {
                    temp--;
                }
            } else {
                mdy[1]++;
                newTime--;
            }
        }
        //TODO: Still need to create checks for overflow
        String startMonth =  parseInt(end.substring(0, 2)) - mdy[0] + "";
        String startYear = parseInt(end.substring(6)) - mdy[2] + "";
        String startDate = parseInt(end.substring(3, 5)) - mdy[1] + "";
        return startMonth + "/" + startDate + "/" + startYear;
    }
    public static String calculateEndDate(String duration, String start) {
        int newTime = parseInt(duration);
        int[] mdy = new int[3];
        int temp = parseInt(start.substring(0, 2));
        while (newTime > 0) {
            if (newTime / 365 != 0) {
                mdy[2]++;
                newTime = newTime - 365;
            } else if (newTime / getDaysInMonth(temp) != 0) {
                mdy[0]++;
                newTime = newTime - getDaysInMonth(temp);
                if (temp == 12) {
                    temp = 1;
                } else {
                    temp++;
                }
            } else {
                mdy[1]++;
                newTime--;
            }
        }
        //TODO: Still need to create checks for overflow
        String startMonth =  parseInt(start.substring(0, 2)) + mdy[0] + "";
        String startYear = parseInt(start.substring(6)) + mdy[2] + "";
        String startDate = parseInt(start.substring(3, 5)) + mdy[1] + "";
        return startMonth + "/" + startDate + "/" + startYear;
    }

    private static int getDaysInMonth(int month) {
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
}
