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