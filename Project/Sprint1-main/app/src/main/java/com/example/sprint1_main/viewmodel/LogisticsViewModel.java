package com.example.sprint1_main.viewmodel;

import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.UserModel;

public class LogisticsViewModel extends ViewModel {




    public LogisticsViewModel() {

    }

    public static void updateNotes(TextView notes) {
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        StringBuilder notes_builder = new StringBuilder();
        for (String note : manager.getCurrentDestination().getNotes()) {
            notes_builder.append(note);
            notes_builder.append("\n");
        }

        notes.setText(notes_builder.toString());

        return;
    }

    public static void updateUsers(TextView contributers) {
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        StringBuilder contributer_builder = new StringBuilder();
        for (UserModel user : manager.getCurrentDestination().getContributingUsers()) {
            contributer_builder.append(user.getUsername());
            contributer_builder.append("\n");
        }

        contributers.setText(contributer_builder.toString());

        return;
    }
}
