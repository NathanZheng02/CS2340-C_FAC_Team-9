package com.example.sprint1_main.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DestinationDatabaseModel {

    private static volatile DestinationDatabaseModel destinationDatabase;



    private List<DestinationModel> destinations;


    private DestinationDatabaseModel() {
        this.destinations = new ArrayList<>();
    }

    public static DestinationDatabaseModel getInstance() {

        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebase.getReference("Destination Database");


        if (destinationDatabase == null) {

            destinationDatabase = new DestinationDatabaseModel();

            DateModel date1 = new DateModel(3, 10, 2009);
            DateModel date2 = new DateModel(4, 10, 2009);

            DateModel date3 = new DateModel(8, 16, 2015);
            DateModel date4 = new DateModel(8, 20, 2015);

            DateModel date5 = new DateModel(1, 1, 2023);
            DateModel date6 = new DateModel(2, 30, 2023);


            DestinationModel paris = new DestinationModel("Paris", date1, date2);
            DestinationModel dublin = new DestinationModel("Dublin", date3, date4);
            DestinationModel newYork = new DestinationModel("New York", date5, date6);

            destinationDatabase.getDestinations().add(paris);
            destinationDatabase.getDestinations().add(dublin);
            destinationDatabase.getDestinations().add(newYork);

            databaseReference.child("Paris").setValue(paris);
            databaseReference.child("Dublin").setValue(dublin);
            databaseReference.child("New York").setValue(newYork);

        } else {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        List<DestinationModel> destinationList = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            DestinationModel destination = ds.getValue(DestinationModel.class);
                            destinationList.add(destination);
                        }
                        destinationDatabase.setDestinations(destinationList);
                    } else {
                        databaseReference.setValue(new ArrayList<>());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        return destinationDatabase;

    }


    public List<DestinationModel> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationModel> destinations) {
        this.destinations = destinations;
    }
}
