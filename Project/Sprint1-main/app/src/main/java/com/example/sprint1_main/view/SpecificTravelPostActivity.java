package com.example.sprint1_main.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.TravelModel;
import com.example.sprint1_main.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SpecificTravelPostActivity extends AppCompatActivity {
    private static final String TAG = "SpecificTravelPostActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifictravelpost);

        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton destination = findViewById(R.id.button_destination);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);
        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton home = findViewById(R.id.button_home);
        Button goBack = findViewById(R.id.goBack);

        EditText userInput = findViewById(R.id.share_user_input);
        Button addUser = findViewById(R.id.share_user_Button);

        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();


        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userInput.getText().toString();

                FirebaseDatabase fb = FirebaseDatabase.getInstance();
                DatabaseReference userRef = fb.getReference("User Database");
                DatabaseReference travRef = fb.getReference("Travel Post Database");

                Query checkUserDatabase = userRef.orderByChild("username").equalTo(username);

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            TravelModel currTrav = manager.getCurrentTravel();
                            List<String> currUsers = currTrav.getUsers();
                            currUsers.add(username);

                            travRef.child(currTrav.getPostNum()
                                    + "").child("contributingUsers").setValue(currUsers);

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


        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpecificTravelPostActivity.this,
                        LogisticsActivity.class);
                startActivity(intent);
            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecificTravelPostActivity.this,
                        DestinationActivity.class);
                startActivity(intent);
            }
        });
        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecificTravelPostActivity.this, DiningActivity.class);
                startActivity(intent);
            }
        });
        accommodations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecificTravelPostActivity.this,
                        AccomodationsActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecificTravelPostActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecificTravelPostActivity.this,
                        TravelCommunityActivity.class);
                startActivity(intent);
            }
        });
        Log.d(TAG, "onCreate called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }
}
