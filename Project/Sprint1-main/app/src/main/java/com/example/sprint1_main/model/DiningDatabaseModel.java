package com.example.sprint1_main.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DiningDatabaseModel {

    private static volatile DiningDatabaseModel diningDatabase;




    private List<ReservationModel> reservations;


    private DiningDatabaseModel() {
        this.reservations = new ArrayList<>();
    }

    public static DiningDatabaseModel getInstance() {

        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebase.getReference("Dining Database");


        if (diningDatabase == null) {

            diningDatabase = new DiningDatabaseModel();
        } else {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        List<ReservationModel> reservationList = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ReservationModel reservation = ds.getValue(ReservationModel.class);
                            reservationList.add(reservation);
                        }
                        diningDatabase.setReservations(reservationList);
                    } else {
                        databaseReference.setValue(new ArrayList<>());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        return diningDatabase;
    }


    public List<ReservationModel> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationModel> reservations) {
        this.reservations = reservations;
    }
}
