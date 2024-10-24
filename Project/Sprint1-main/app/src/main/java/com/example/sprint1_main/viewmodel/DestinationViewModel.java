package com.example.sprint1_main.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sprint1_main.model.DestinationModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DestinationViewModel extends ViewModel {
    MutableLiveData<Integer> duration;
    MutableLiveData<String> startDate;
    MutableLiveData<String> endDate;

    private DestinationModel destinationModel;
    private DatabaseReference databaseReference;

    public DestinationViewModel() {
        duration = new MutableLiveData<>();
        startDate = new MutableLiveData<>();
        endDate = new MutableLiveData<>();
        destinationModel = new DestinationModel();

        startDate.setValue(destinationModel.getStartDate());
        endDate.setValue(destinationModel.getEndDate());

        //initialize database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Read data from firebase
        databaseReference.child("user/testuser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //counter exists, retrieve the value
                    String firebaseCounter = dataSnapshot.getValue(String.class);

                    if (firebaseCounter != null) {
                        startDate.setValue(firebaseCounter);
                        endDate.setValue(firebaseCounter);
                        destinationModel.setStartDate(firebaseCounter);
                    }
                } else {
                    //counter does not exist
                    destinationModel.setStartDate("MM/DD/YYYY");
                    startDate.setValue("MM/DD/YYYY");
                    databaseReference.child("user/testuser").setValue("MM/DD/YYYY");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Handle errors
            }
        });
    }
    public LiveData<Integer> getDuration() {
        return duration;
    }

    public LiveData<String> getStartDate() {
        return startDate;
    }

    public LiveData<String> getEndDate() {
        return endDate;
    }
}