package com.example.sprint1_main.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import com.example.sprint1_main.R;

public class TravelCommunityActivity extends AppCompatActivity {

    private final String TAG = "TravelCommunityActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelcommunity);

        Button logistics = findViewById(R.id.button_logistics);
        Button destination = findViewById(R.id.button_destination);
        Button dining = findViewById(R.id.button_diningEstablishments);
        Button accommodations = findViewById(R.id.button_accommodations);
        Button home = findViewById(R.id.button_home);

        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TravelCommunityActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelCommunityActivity.this, DestinationActivity.class);
                startActivity(intent);
            }
        });
        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelCommunityActivity.this, DiningActivity.class);
                startActivity(intent);
            }
        });
        accommodations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelCommunityActivity.this, AccomodationsActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelCommunityActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        Log.d(TAG, "onCreate called");

    }




    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }
}
