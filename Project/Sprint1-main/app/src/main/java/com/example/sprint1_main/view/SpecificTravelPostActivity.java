package com.example.sprint1_main.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.LodgingModel;
import com.example.sprint1_main.model.ReservationModel;
import com.example.sprint1_main.model.TravelModel;

public class SpecificTravelPostActivity extends AppCompatActivity {
    private static final String TAG = "SpecificTravelPostActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifictravelpost);

        TravelModel travel = (TravelModel) getIntent().getSerializableExtra("travelDetails");

        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton destination = findViewById(R.id.button_destination);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);
        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton home = findViewById(R.id.button_home);
        Button goBack = findViewById(R.id.goBack);

        TextView usernameTextView = findViewById(R.id.userLabel);
        TextView startDateTextView = findViewById(R.id.startDate);
        TextView endDateTextView = findViewById(R.id.endDate);
        TextView destinationsTextView = findViewById(R.id.destinationsLabel);
        TextView transportationTextView = findViewById(R.id.transportationsLabel);
        TextView accommodationsTextView = findViewById(R.id.accommodationsLabel);
        TextView reservationsTextView = findViewById(R.id.reservationsLabel);
        TextView notesTextView = findViewById(R.id.notesLabel);

        if (travel != null) {
            usernameTextView.setText(travel.getUser().getUsername());
            startDateTextView.setText(travel.getStartDate().toString());
            endDateTextView.setText(travel.getEndDate().toString());

            StringBuilder accommodationsBuilder = new StringBuilder();
            for (DestinationModel dest : travel.getDestinations()) {
                for (LodgingModel accommodation : dest.getLodgings()) {
                    accommodationsBuilder.append(accommodation.getLocation()).append("\n");
                }
            }
            accommodationsTextView.setText(accommodationsBuilder.toString());

            StringBuilder reservationsBuilder = new StringBuilder();

            for (DestinationModel dest : travel.getDestinations()) {
                for (ReservationModel reservation : dest.getReservations()) {
                    reservationsBuilder.append(reservation.getLocation()).append("\n");
                }
            }
            reservationsTextView.setText(reservationsBuilder.toString());
            notesTextView.setText(travel.getNotes().toString());

            StringBuilder destinationsBuilder = new StringBuilder();
            for (DestinationModel dest : travel.getDestinations()) {
                destinationsBuilder.append(dest.getDestinationName()).append("\n");
            }
            destinationsTextView.setText(destinationsBuilder.toString());
        }

        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpecificTravelPostActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecificTravelPostActivity.this, DestinationActivity.class);
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
                Intent intent = new Intent(SpecificTravelPostActivity.this, AccomodationsActivity.class);
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
                Intent intent = new Intent(SpecificTravelPostActivity.this, TravelCommunityActivity.class);
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
