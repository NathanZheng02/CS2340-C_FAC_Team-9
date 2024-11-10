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

                if (manager.getCurrentDestination().getContributingUsers() == null) {
                    manager.getCurrentDestination().setContributingUsers(new ArrayList<>());
                }



                Query checkUserDatabase = reference.orderByChild("username").equalTo(username);

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel user = snapshot.child(username).getValue(UserModel.class);
                            manager.getCurrentDestination().getContributingUsers().add(user);

                            DatabaseReference ref1 =
                                    fb.getReference("User Database").child(user.getUsername());
                            DatabaseReference ref2 =
                                    ref1.child("destinations");
                            List<DestinationModel> dL = manager.getCurrentUser().getDestinations();
                            DestinationModel currDes = manager.getCurrentDestination();
                            ref2.child("" + dL.size()).setValue(currDes);


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
