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
