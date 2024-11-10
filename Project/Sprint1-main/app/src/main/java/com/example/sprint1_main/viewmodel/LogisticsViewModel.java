package com.example.sprint1_main.viewmodel;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LogisticsViewModel extends ViewModel {




    public LogisticsViewModel() {

    }

    public static void updateDestinations() {
        DatabaseReference dF = FirebaseDatabase.getInstance().getReference("User Database");
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        DatabaseReference destinations = dF.child("destinations");

        List<DestinationModel> desList = new ArrayList<>();

        destinations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        DestinationModel des = dataSnapshot.getValue(DestinationModel.class);
                        desList.add(des);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        manager.getCurrentUser().setDestinations(desList);
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
//        DatabaseReference dF = FirebaseDatabase.getInstance().getReference("Destination Database");
//        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();
//
//        String currDes = manager.getCurrentDestination().getDestinationName();
//
//        DatabaseReference users = dF.child(currDes).child("contributingUsers");
//
//        List<UserModel> userList = new ArrayList<>();
//
//        users.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        UserModel user = dataSnapshot.getValue(UserModel.class);
//                        userList.add(user);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        StringBuilder contributerBuilder = new StringBuilder();
//        if (userList != null) {
//            for (UserModel user : userList) {
//                contributerBuilder.append(user.getUsername());
//                contributerBuilder.append("\n");
//            }
//        }
//
//
//        contributers.setText(contributerBuilder.toString());

        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        StringBuilder userBuilder = new StringBuilder();
        if (manager.getCurrentDestination().getContributingUsers() != null) {
            for (String user : manager.getCurrentDestination().getContributingUsers()) {
                userBuilder.append(user);
                userBuilder.append("\n");
            }
        }


        contributers.setText(userBuilder.toString());

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
