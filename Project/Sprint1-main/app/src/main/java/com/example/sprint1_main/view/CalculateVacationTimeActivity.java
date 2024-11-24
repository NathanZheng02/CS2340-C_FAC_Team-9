package com.example.sprint1_main.view;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DateCalculatorModel;
import com.example.sprint1_main.model.DateModel;
import com.example.sprint1_main.model.UserModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CalculateVacationTimeActivity extends AppCompatActivity {

    private static final String TAG = "CalculateTravelActivity";

    private FirebaseDatabase database;
    private DatabaseReference reference;

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
                Intent i = new Intent(CalculateVacationTimeActivity.this, LogisticsActivity.class);
                startActivity(i);
            }
        });
        accommodations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CalculateVacationTimeActivity.this,
                        AccomodationsActivity.class);
                startActivity(i);
            }
        });
        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CalculateVacationTimeActivity.this, DiningActivity.class);
                startActivity(i);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CalculateVacationTimeActivity.this,
                        TravelCommunityActivity.class);
                startActivity(i);
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
                Intent i = new Intent(CalculateVacationTimeActivity.this,
                        DestinationActivity.class);
                startActivity(i);
            }
        });
        logTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CalculateVacationTimeActivity.this, LogTravelActivity.class);
                startActivity(i);
            }
        });
        calcVacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CalculateVacationTimeActivity.this,
                        CalculateVacationTimeActivity.class);
                startActivity(i);
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateCalculatorModel dateCalculator = new DateCalculatorModel();
                String durationString = duration.getText().toString().trim();
                String startString = start.getText().toString().trim();
                String endString = end.getText().toString().trim();
                DateModel startDate = null;
                DateModel endDate = null;
                int durationInt = 0;

                if (durationString.isEmpty() && startString.isEmpty() && endString.isEmpty()) {
                    duration.setError("Please input at least 2 conditions");
                } else if (durationString.isEmpty() && startString.isEmpty()) {
                    duration.setError("Please input at least 2 conditions");
                } else if (durationString.isEmpty() && endString.isEmpty()) {
                    duration.setError("Please input at least 2 conditions");
                } else if (startString.isEmpty() && endString.isEmpty()) {
                    start.setError("Please input at least 2 conditions");
                } else if (durationString.isEmpty()) {
                    String[] startList = startString.split("/");
                    startDate = new DateModel(parseInt(startList[0]), parseInt(startList[1]),
                            parseInt(startList[2]));
                    String[] endList = endString.split("/");
                    endDate = new DateModel(parseInt(endList[0]), parseInt(endList[1]),
                            parseInt(endList[2]));
                    durationInt = dateCalculator.getDuration(startDate, endDate);

                } else if (startString.isEmpty()) {
                    String[] endList = endString.split("/");
                    endDate = new DateModel(parseInt(endList[0]), parseInt(endList[1]),
                            parseInt(endList[2]));
                    durationInt = parseInt(durationString);
                    startDate = dateCalculator.getStartDate(endDate, durationInt);

                } else if (endString.isEmpty()) {
                    String[] startList = startString.split("/");
                    startDate = new DateModel(parseInt(startList[0]), parseInt(startList[1]),
                            parseInt(startList[2]));
                    durationInt = parseInt(durationString);
                    endDate = dateCalculator.getEndDate(startDate, durationInt);
                }

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("User Database");
                ApplicationManagerModel manager = ApplicationManagerModel.getInstance();
                UserModel currentUser = manager.getCurrentUser();

                manager.getCurrentUser().setDuration(parseInt(durationString));

                DatabaseReference ref = reference.child(currentUser.getUsername());

                if (startDate != null && endDate != null) {
                    ref.child("startDate").setValue(startDate);
                    ref.child("endDate").setValue(endDate);
                    ref.child("duration").setValue(durationInt);
                }

                Toast.makeText(CalculateVacationTimeActivity.this,
                        "Calculation successful and duration was stored!",
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CalculateVacationTimeActivity.this, LogisticsActivity.class);
                startActivity(i);

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
