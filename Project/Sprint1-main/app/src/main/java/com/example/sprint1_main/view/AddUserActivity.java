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
import java.util.List;

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

//                if (manager.getCurrentDestination().getContributingUsers() == null) {
//                    manager.getCurrentDestination().setContributingUsers(new ArrayList<>());
//                    manager.getCurrentDestination().getContributingUsers().add(manager.getCurrentUser());
//                }



                Query checkUserDatabase = reference.orderByChild("username").equalTo(username);

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel user = snapshot.child(username).getValue(UserModel.class);

                            if (manager.getCurrentDestination().getContributingUsers() == null) {
                                manager.getCurrentDestination().setContributingUsers(new ArrayList<>());
                                manager.getCurrentDestination().getContributingUsers().add(manager.getCurrentUser().getUsername());
                            }

                            manager.getCurrentDestination().getContributingUsers().add(user.getUsername());

                            DestinationModel currDes = manager.getCurrentDestination();

                            //add user to destination's users
                            DatabaseReference ref = fb.getReference("Destination Database");
                            DatabaseReference ref2 = ref.child(currDes.getDestinationName());
                            DatabaseReference ref3 = ref2.child("contributingUsers").child("" + (currDes.getContributingUsers().size()-1));
                            ref3.setValue(user.getUsername());

                            //add destination to new user
                            DatabaseReference ref4 = fb.getReference("User Database");
                            DatabaseReference ref5 = ref4.child(user.getUsername()).child("destinations");

                            DatabaseReference ref6;
                            if (user.getDestinations() != null) {
                                ref6 = ref5.child("" + (user.getDestinations().size()));
                            } else {
                                ref6 = ref5.child("0");
                            }

                            ref6.setValue(currDes);

//                            //update destination for current user
//                            DatabaseReference ref7 = ref4.child(manager.getCurrentUser().getUsername());
//                            int index = manager.getCurrentUser().getDestinations().indexOf(currDes);
//                            DatabaseReference ref8 = ref7.child("destinations").child("" + index);
//                            ref8.setValue(currDes);

                            manager.updateUserDestinations();









//                            DatabaseReference ref3 =
//                                    fb.getReference("Destination Database").child(currDes.getDestinationName());
//                            DatabaseReference ref4 =
//                                    ref3.child("contributingUsers").child("" + (currDes.getContributingUsers().size()-1));
//                            ref4.setValue(user);
//
//
//                            DatabaseReference ref5 =
//                                    fb.getReference("User Database").child(user.getUsername());
//                            DatabaseReference ref6 =
//                                    ref5.child("destinations").child("" + (user.getDestinations().size()-1));
//                            ref6.setValue(currDes);

//                            manager.getCurrentDestination().getContributingUsers().add(user);











//
//                            manager.getCurrentDestination().getContributingUsers().add(user);
//
//                            DatabaseReference ref1 =
//                                    fb.getReference("User Database").child(user.getUsername());
//                            DatabaseReference ref2 =
//                                    ref1.child("destinations");
//                            List<DestinationModel> dL = manager.getCurrentUser().getDestinations();
//
//                            ref2.child("" + (dL.size()-1)).setValue(currDes);





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
