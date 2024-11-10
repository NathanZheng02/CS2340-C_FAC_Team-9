package com.example.sprint1_main.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseModel {

    private static volatile UserDatabaseModel userDatabase;



    private List<UserModel> users;


    private UserDatabaseModel() {
        this.users = new ArrayList<>();
    }

    public static UserDatabaseModel getInstance() {

        DatabaseReference dF = FirebaseDatabase.getInstance().getReference("User Database");


        if (userDatabase == null) {
            userDatabase = new UserDatabaseModel();
        } else {
            dF.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        List<UserModel> userList = new ArrayList<>();

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            UserModel user = dataSnapshot.getValue(UserModel.class);
                            userList.add(user);
                        }

                        userDatabase.setUsers(userList);
                    } else {
                        dF.setValue(new ArrayList<>());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        return userDatabase;

    }


    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
