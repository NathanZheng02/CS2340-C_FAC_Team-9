package com.example.sprint1_main.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sprint1_main.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<String> username;
    private MutableLiveData<String> password;

    private DatabaseReference databaseReference;
    private UserModel userModel;

    public LoginViewModel() {
        username = new MutableLiveData<>();
        password = new MutableLiveData<>();

        // initialize firebase data reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //read data from firebase
        databaseReference.child(String.valueOf(username)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String correctPassword = dataSnapshot.getValue(String.class);

                    if(String.valueOf(password).equals(correctPassword)) {
                        //allow user to login and go to home screen
                        userModel.setUsername(username);
                        userModel.setPassword(password);
                    }
                }

                else {
                    //username does not exist,
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })
    }
}
