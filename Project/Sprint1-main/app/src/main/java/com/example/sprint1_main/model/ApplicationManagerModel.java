package com.example.sprint1_main.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ApplicationManagerModel {
    private static volatile ApplicationManagerModel applicationManager;

    private UserModel currentUser;
    private DestinationModel currentDestination;


    private ApplicationManagerModel() {
        this.currentUser = new UserModel("email", "number", "name", 10, "tempUser", "password");
        this.currentDestination = null;
    }

    public static ApplicationManagerModel getInstance() {
        if (applicationManager == null) {
            synchronized (ApplicationManagerModel.class) {
                if (applicationManager == null) {
                    applicationManager = new ApplicationManagerModel();
                }
            }
        }
        return applicationManager;
    }

    public void updateUserDestinations() {
        DestinationDatabaseModel destinationDatabaseModel = DestinationDatabaseModel.getInstance();
        UserDatabaseModel userDatabaseModel = UserDatabaseModel.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("User Database");
        DatabaseReference desRef = database.getReference("Destination Database");

        List<DestinationModel> desList = new ArrayList<>();

        for (DestinationModel des : destinationDatabaseModel.getDestinations()) {
            String check = des.getDestinationName();
            Query checkUserDatabase = desRef.orderByChild("destinationName").equalTo(check);

            checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

                        DataSnapshot desSnap = snapshot.child(des.getDestinationName());
                        DestinationModel destination = desSnap.getValue(DestinationModel.class);

                        if (destination != null && destination.getContributingUsers() != null) {
                            List<String> userList = destination.getContributingUsers();


                            for (String user : userList) {
                                if (user.equals(manager.getCurrentUser().getUsername())) {
                                    desList.add(des);
                                }
                            }

                            DatabaseReference ref1 =
                                    userRef.child(manager.getCurrentUser().getUsername());
                            ref1.child("destinations").setValue(desList);
                            manager.getCurrentUser().setDestinations(desList);
                        }

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });






        }


    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    public DestinationModel getCurrentDestination() {
        return currentDestination;
    }

    public void setCurrentDestination(DestinationModel currentDestination) {
        this.currentDestination = currentDestination;
    }
}
