package com.example.sprint1_main.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccommodationDatabaseModel {

    private static volatile AccommodationDatabaseModel accommodationDatabase;




    private List<LodgingModel> lodgings;


    private AccommodationDatabaseModel() {
        this.lodgings = new ArrayList<>();
    }

    public static AccommodationDatabaseModel getInstance() {

        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebase.getReference("Accommodation Database");


        if (accommodationDatabase == null) {

            accommodationDatabase = new AccommodationDatabaseModel();
        } else {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        List<LodgingModel> lodgingList = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            LodgingModel lodging = ds.getValue(LodgingModel.class);
                            lodgingList.add(lodging);
                        }
                        accommodationDatabase.setLodgings(lodgingList);
                    } else {
                        databaseReference.setValue(new ArrayList<>());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        return accommodationDatabase;
    }

    public List<LodgingModel> getLodgings() {
        return lodgings;
    }

    public void setLodgings(List<LodgingModel> lodgings) {
        this.lodgings = lodgings;
    }
}
