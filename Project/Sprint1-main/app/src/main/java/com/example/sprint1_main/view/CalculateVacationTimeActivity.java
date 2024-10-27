package com.example.sprint1_main.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.sprint1_main.R;
import com.example.sprint1_main.viewmodel.DestinationViewModel;

public class CalculateVacationTimeActivity extends AppCompatActivity {

    private static final String TAG = "CalculateTravelActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatevacationtime);

        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);
        ImageButton community = findViewById(R.id.button_travelCommunity);
        ImageButton home = findViewById(R.id.button_home);
        ImageButton destination = findViewById(R.id.button_destination);
        Button logTravel = findViewById(R.id.button_logTravel);
        Button calcVacation = findViewById(R.id.button_vacationTime);
        Button calculate = findViewById(R.id.button_calculate);

        EditText start = findViewById(R.id.start);
        EditText end = findViewById(R.id.end);
        EditText duration = findViewById(R.id.duration);

        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculateVacationTimeActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
        accommodations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculateVacationTimeActivity.this, AccomodationsActivity.class);
                startActivity(intent);
            }
        });
        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculateVacationTimeActivity.this, DiningActivity.class);
                startActivity(intent);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculateVacationTimeActivity.this, TravelCommunityActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculateVacationTimeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculateVacationTimeActivity.this, DestinationActivity.class);
                startActivity(intent);
            }
        });
        logTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculateVacationTimeActivity.this, LogTravelActivity.class);
                startActivity(intent);
            }
        });
        calcVacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculateVacationTimeActivity.this, CalculateVacationTimeActivity.class);
                startActivity(intent);
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = duration.getText().toString().trim();
                String startDate = start.getText().toString().trim();
                String endDate = end.getText().toString().trim();
                String emptyUpdates = "";
                if (time.isEmpty() || time == null) {
                    if (startDate == null || endDate == null) {
                        duration.setError("Please input at least 2 conditions");
                    } else {
                        DestinationViewModel.calculateDuration(startDate, endDate);
                        emptyUpdates = "time";
                    }
                } else if (startDate.isEmpty() || startDate == null) {
                    if (time == null || endDate == null) {
                        start.setError("Please input at least 2 conditions");
                    } else {
                        DestinationViewModel.calculateStartDate(time, endDate);
                        emptyUpdates = "startDate";
                    }
                } else if (endDate.isEmpty() || endDate == null) {
                    if (startDate == null || time == null) {
                        end.setError("Please input at least 2 conditions");
                    } else {
                        DestinationViewModel.calculateEndDate(time, startDate);
                        emptyUpdates = "endDate";
                    }
                }
                //TODO: Update Firebase with new Start, End, and duration times


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
