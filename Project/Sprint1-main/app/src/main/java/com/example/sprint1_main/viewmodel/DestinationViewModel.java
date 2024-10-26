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
    private static UserModel currentUser;

    public DestinationViewModel() {

    }
    private static void checkInputs(EditText duration, EditText start, EditText end) {
        //Assuming the time is in days, Start Dates and End Dates have formats of MM/DD/YYYY
        String time = duration.getText().toString().trim();
        String startDate = start.getText().toString().trim();
        String endDate = end.getText().toString().trim();
        if (time == null) {
            if (startDate == null || endDate == null) {
                start.setError("Enter 2 parameters minimum");
                end.setError("Enter 2 parameters minimum");
            } else {
                int month = parseInt(endDate.substring(0, 2)) - parseInt(startDate.substring(0, 2));
                int day = parseInt(endDate.substring(3, 5)) - parseInt(startDate.substring(3, 5));
                int year = parseInt(endDate.substring(6)) - parseInt(startDate.substring(6));
                duration.setText((year * 365 + month * 30 + day));
            }
        } else if (endDate == null) {
            if (time == null || startDate == null) {
                start.setError("Enter 2 parameters minimum");
                duration.setError("Enter 2 parameters minimum");
            } else {
                /*int newTime = parseInt(time);
                int[] mdy = new int[3];
                while (newTime > 0) {
                    if (newTime / 365 != 0) {
                        mdy[2]++;
                        newTime = newTime % 365;
                    } else if (newTime / 30 != 0) {
                        mdy[0]++;
                        newTime = newTime % 30;
                    } else {
                        mdy[1]++;
                        newTime--;
                    }
                }
                currentUser.setEndDate();

                 */
            }
        } else if (startDate == null) {
            if (time == null || endDate == null) {
                end.setError("Enter 2 parameters minimum");
                duration.setError("Enter 2 parameters minimum");
            } else {

            }
        }
    }
    public static void calculateDuration(EditText duration, EditText start, EditText end) {
        checkInputs(duration, start, end);
    }
    public static void updateCurrentUser(UserModel user) {
        if (user != null) {
            currentUser = user;
        }
    }
}
