package com.example.sprint1_main.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserUpdater implements Observer {

    private TravelPostData travelPostData;

    public UserUpdater(TravelPostData travelPostData) {
        this.travelPostData = travelPostData;
        travelPostData.registerObserver(this);
    }

    @Override
    public void update(List<UserModel> users, List<TravelModel> travels,
                       List<DestinationModel> destinations) {

        for (TravelModel trav : travels) {
            for (DestinationModel dest : trav.getDestinations()) {
                for (String u : trav.getUsers()) {
                    if (dest.getContributingUsers() == null) {
                        dest.setContributingUsers(new ArrayList<>());
                    }
                    if (!u.equals("defUser") && !dest.getContributingUsers().contains(u)) {
                        dest.getContributingUsers().add(u);

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference desRef = database.getReference("Destination Database");

                        DatabaseReference ref1 =
                                desRef.child(dest.getDestinationName()).child("contributingUsers");
                        ref1.setValue(dest.getContributingUsers());

                    }
                }
            }
        }
    }

}
