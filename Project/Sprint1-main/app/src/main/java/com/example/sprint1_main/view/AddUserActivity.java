package com.example.sprint1_main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity {

    private static final String TAG = "AddUserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);

        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        Button confirm = findViewById(R.id.confirm_user_Button);
        Button cancel = findViewById(R.id.user_return_logistics_Button);
        EditText userInput = findViewById(R.id.add_user_input);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userInput.getText().toString();

                FirebaseDatabase fb = FirebaseDatabase.getInstance();
                DatabaseReference reference = fb.getReference("User Database");




                Query checkUserDatabase = reference.orderByChild("username").equalTo(username);

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel user = snapshot.child(username).getValue(UserModel.class);

                            DestinationModel currDes = manager.getCurrentDestination();
                            UserModel currUser = manager.getCurrentUser();

                            if (manager.getCurrentDestination().getContributingUsers() == null) {
                                currDes.setContributingUsers(new ArrayList<>());
                                currDes.getContributingUsers().add(currUser.getUsername());
                            }

                            currDes.getContributingUsers().add(user.getUsername());


                            //add user to destination's users
                            DatabaseReference ref = fb.getReference("Destination Database");
                            DatabaseReference ref2 = ref.child(currDes.getDestinationName());
                            DatabaseReference ref3 =
                                    ref2.child("contributingUsers");
                            DatabaseReference ref4 =
                                    ref3.child("" + (currDes.getContributingUsers().size() - 1));
                            ref4.setValue(user.getUsername());

                            //add destination to new user
                            DatabaseReference userRef = fb.getReference("User Database");
                            DatabaseReference ref5 =
                                    userRef.child(user.getUsername()).child("destinations");

                            DatabaseReference ref6;
                            if (user.getDestinations() != null) {
                                ref6 = ref5.child("" + (user.getDestinations().size()));
                            } else {
                                ref6 = ref5.child("0");
                            }

                            ref6.setValue(currDes);


                            manager.updateUserDestinations();






                            Intent i = new Intent(AddUserActivity.this, LogisticsActivity.class);
                            startActivity(i);
                        } else {
                            userInput.setError("User Does Not Exist");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddUserActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
    }
}
