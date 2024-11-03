package com.example.sprint1_main.viewmodel;

import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.UserModel;

public class LogisticsViewModel extends ViewModel {




    public LogisticsViewModel() {

    }

    public static void updateNotes(TextView notes) {
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        StringBuilder notesBuilder = new StringBuilder();
        if (manager.getCurrentDestination().getNotes() != null) {
            for (String note : manager.getCurrentDestination().getNotes()) {
                notesBuilder.append(note);
                notesBuilder.append("\n");
            }
        }


        notes.setText(notesBuilder.toString());

        return;
    }

    public static void updateUsers(TextView contributers) {
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        StringBuilder contributerBuilder = new StringBuilder();
        if (manager.getCurrentDestination().getContributingUsers() != null) {
            for (UserModel user : manager.getCurrentDestination().getContributingUsers()) {
                contributerBuilder.append(user.getUsername());
                contributerBuilder.append("\n");
            }
        }


        contributers.setText(contributerBuilder.toString());

        return;
    }

    public static void updateDays(TextView totalDays) {

        int days = getDays();

        String daysString = "Total Days on Vacation: " + days;
        totalDays.setText(daysString);
    }

    public static int getDays() {
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        int days = 0;
        for (DestinationModel destination : manager.getCurrentUser().getDestinations()) {
            days += destination.getEstimatedDays();
        }

        return days;
    }
}
