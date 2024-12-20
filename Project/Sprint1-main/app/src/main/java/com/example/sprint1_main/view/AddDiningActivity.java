package com.example.sprint1_main.view;

import static java.lang.Integer.parseInt;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DateModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.DiningDatabaseModel;
import com.example.sprint1_main.model.ReservationModel;
import com.example.sprint1_main.model.TimeModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddDiningActivity extends AppCompatActivity {

    private static final String TAG = "AddDiningActivity";

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddining);

        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton destination = findViewById(R.id.button_destination);
        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton community = findViewById(R.id.button_travelCommunity);
        ImageButton home = findViewById(R.id.button_home);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);

        EditText diningTime = findViewById(R.id.diningTime);
        EditText diningDate = findViewById(R.id.diningDate);
        EditText diningLocation = findViewById(R.id.diningLocation);
        EditText diningWebsite = findViewById(R.id.diningWebsite);

        Button addReservation = findViewById(R.id.button_addReservation);

        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDiningActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDiningActivity.this, DestinationActivity.class);
                startActivity(intent);
            }
        });
        accommodations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDiningActivity.this, AccomodationsActivity.class);
                startActivity(intent);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDiningActivity.this, TravelCommunityActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDiningActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDiningActivity.this, DiningActivity.class);
                startActivity(intent);
            }
        });
        addReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Dining Database");

                // Time represented as MM/DD/YYYY HH:MM in military time
                String time = diningTime.getText().toString().trim();
                String date = diningDate.getText().toString().trim();
                String location = diningLocation.getText().toString().trim();
                String website = diningWebsite.getText().toString().trim();

                String[] dateList = date.split("/");
                if (dateList.length != 3) {
                    diningDate.setError("Date Must Be In Format MM/DD/YYYY");
                    return;
                }
                int month = parseInt(dateList[0]);
                int day = parseInt(dateList[1]);
                int year = parseInt(dateList[2]);

                String[] timeList = time.split(":");
                if (timeList.length != 2) {
                    diningTime.setError("Date Must Be In Format HH:MM");
                    return;
                }
                int hour = parseInt(timeList[0]);
                int minute = parseInt(timeList[1]);

                TimeModel timeModel = new TimeModel(hour, minute);
                DateModel dateModel = new DateModel(month, day, year);
                ReservationModel reservation = new ReservationModel(location, website,
                        dateModel, timeModel);

                ApplicationManagerModel manager = ApplicationManagerModel.getInstance();


                if (manager.getCurrentDestination().getReservations() == null) {
                    manager.getCurrentDestination().setReservations(new ArrayList<>());
                }

                for (ReservationModel res : manager.getCurrentDestination().getReservations()) {
                    if (res.getLocation().equals(location)) {
                        diningLocation.setError("Location Must Not Be Added To Destination");
                        return;
                    }
                }

                manager.getCurrentDestination().getReservations().add(reservation);

                DiningDatabaseModel diningDatabase = DiningDatabaseModel.getInstance();
                reference.child(location).setValue(reservation);

                DatabaseReference ref2 = database.getReference("Destination Database");

                DestinationModel currDes = manager.getCurrentDestination();

                DatabaseReference ref3 = ref2.child(currDes.getDestinationName());
                ref3.child("reservations").setValue(currDes.getReservations());

                manager.updateUserDestinations();


                Intent intent = new Intent(AddDiningActivity.this,  DiningActivity.class);
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