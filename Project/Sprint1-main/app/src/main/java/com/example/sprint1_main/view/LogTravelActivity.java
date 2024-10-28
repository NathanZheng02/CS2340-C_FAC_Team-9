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

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DateModel;
import com.example.sprint1_main.model.UserModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.view.LoginActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogTravelActivity extends AppCompatActivity {


    private static final String TAG = "LogTravelActivity";

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logtravel);

        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);
        ImageButton community = findViewById(R.id.button_travelCommunity);
        ImageButton home = findViewById(R.id.button_home);
        Button logTravelButton = findViewById(R.id.button_logTravel);
        Button calcVacation = findViewById(R.id.button_vacationTime);
        Button submit = findViewById(R.id.submitButton);
        Button cancel = findViewById(R.id.cancelButton);
        EditText destination = findViewById(R.id.travellocation);
        EditText startDateField = findViewById(R.id.startDate);
        EditText endDateField = findViewById(R.id.endDate);
        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogTravelActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
        accommodations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogTravelActivity.this, AccomodationsActivity.class);
                startActivity(intent);
            }
        });
        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogTravelActivity.this, DiningActivity.class);
                startActivity(intent);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogTravelActivity.this, TravelCommunityActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogTravelActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        logTravelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogTravelActivity.this, LogTravelActivity.class);
                startActivity(intent);
            }
        });
        calcVacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogTravelActivity.this, CalculateVacationTimeActivity.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogTravelActivity.this, DestinationActivity.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Destination Database");



                String destinationName = destination.getText().toString();
                String startDate = startDateField.getText().toString().trim();
                String endDate = endDateField.getText().toString().trim();
                //MM/DD/YYYY
                DateModel beginning = new DateModel(parseInt(startDate.substring(0, 2)), parseInt(startDate.substring(3, 5)), parseInt(startDate.substring(6)));
                DateModel ending = new DateModel(parseInt(endDate.substring(0, 2)), parseInt(endDate.substring(3, 5)), parseInt(endDate.substring(6)));

                DestinationModel destination = new DestinationModel(destinationName, beginning, ending);

                reference.child(destinationName).setValue(destination);




                ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

                manager.getCurrentUser().getDestinations().add(destination);

                FirebaseDatabase.getInstance().getReference("User Database").child(manager.getCurrentUser().getUsername()).child("destinations").child("" + manager.getCurrentUser().getDestinations().size()).setValue(destination);

                Intent intent = new Intent(LogTravelActivity.this, DestinationActivity.class);
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
