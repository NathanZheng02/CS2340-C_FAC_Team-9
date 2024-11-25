package com.example.sprint1_main.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TravelDatabaseModel {

    private static volatile TravelDatabaseModel travelDatabase;



    private List<TravelModel> travels;


    private TravelDatabaseModel() {
        this.travels = new ArrayList<>();


    }

    public static TravelDatabaseModel getInstance() {

        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebase.getReference("Travel Post Database");


        if (travelDatabase == null) {
            travelDatabase = new TravelDatabaseModel();

            UserModel defUser = new UserModel("1", "1", "1", 1,
                    "defUser", "password");

            DestinationModel defDest1 = new DestinationModel("Paris",
                    new DateModel(1, 1, 1), new DateModel(1, 1, 1));
            DestinationModel defDest2 = new DestinationModel("London",
                    new DateModel(1, 1, 1), new DateModel(1, 1, 1));
            DestinationModel defDest3 = new DestinationModel("Berlin",
                    new DateModel(1, 1, 1), new DateModel(1, 1, 1));
            DestinationModel defDest4 = new DestinationModel("New York",
                    new DateModel(1, 1, 1), new DateModel(1, 1, 1));

            TravelModel trav1 = new TravelModel(defUser, new DateModel(1, 1, 1),
                    new DateModel(1, 1, 1));
            trav1.getDestinations().add(defDest1);

            TravelModel trav2 = new TravelModel(defUser, new DateModel(1, 1, 1),
                    new DateModel(1, 1, 1));
            trav2.getDestinations().add(defDest2);

            TravelModel trav3 = new TravelModel(defUser, new DateModel(1, 1, 1),
                    new DateModel(1, 1, 1));
            trav3.getDestinations().add(defDest3);
            trav3.getDestinations().add(defDest4);

            travelDatabase.travels.add(trav1);
            travelDatabase.travels.add(trav2);
            travelDatabase.travels.add(trav3);

            databaseReference.child("0").setValue(trav1);
            databaseReference.child("1").setValue(trav2);
            databaseReference.child("2").setValue(trav3);

        } else {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        List<TravelModel> travelList = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            TravelModel travel = ds.getValue(TravelModel.class);
                            travelList.add(travel);
                        }
                        travelDatabase.setTravels(travelList);
                    } else {
                        databaseReference.setValue(new ArrayList<>());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        return travelDatabase;

    }


    public List<TravelModel> getTravels() {
        return travels;
    }

    public void setTravels(List<TravelModel> travels) {
        this.travels = travels;
    }
}
