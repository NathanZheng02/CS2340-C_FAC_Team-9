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

    private volatile static DestinationDatabaseModel destinationDatabase;



    private List<DestinationModel> destinations;


    private DestinationDatabaseModel() {
        this.destinations = new ArrayList<>();
    }

    public static DestinationDatabaseModel getInstance() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Destination Database");


        if (destinationDatabase == null) {

            destinationDatabase = new DestinationDatabaseModel();

            DestinationModel paris = new DestinationModel("Paris", new DateModel(3, 10, 2009), new DateModel(4,10,2009));
            DestinationModel dublin = new DestinationModel("Dublin", new DateModel(8, 16, 2015), new DateModel(8,20,2015));
            DestinationModel new_york = new DestinationModel("New York", new DateModel(1, 1, 2023), new DateModel(2,30,2023));

            destinationDatabase.getDestinations().add(paris);
            destinationDatabase.getDestinations().add(dublin);
            destinationDatabase.getDestinations().add(new_york);

            databaseReference.child("Paris").setValue(paris);
            databaseReference.child("Dublin").setValue(dublin);
            databaseReference.child("New York").setValue(new_york);

        } else {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        List<DestinationModel> destinationList = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            DestinationModel destination = dataSnapshot.getValue(DestinationModel.class);
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
