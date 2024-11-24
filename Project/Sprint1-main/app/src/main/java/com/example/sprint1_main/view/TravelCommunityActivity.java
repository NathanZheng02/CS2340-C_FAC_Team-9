package com.example.sprint1_main.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.ImageButton;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.DestinationDatabaseModel;
import com.example.sprint1_main.model.TravelDatabaseModel;
import com.example.sprint1_main.model.TravelPostData;
import com.example.sprint1_main.model.TravelUpdater;
import com.example.sprint1_main.model.UserDatabaseModel;
import com.example.sprint1_main.model.UserUpdater;

public class TravelCommunityActivity extends AppCompatActivity {

    private static final String TAG = "TravelCommunityActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelcommunity);


        TravelPostData travelPostData = new TravelPostData();

        UserUpdater userUpdater = new UserUpdater(travelPostData);
        TravelUpdater travelUpdater = new TravelUpdater(travelPostData);

        UserDatabaseModel userDatabase = UserDatabaseModel.getInstance();
        TravelDatabaseModel travelDatabase = TravelDatabaseModel.getInstance();
        DestinationDatabaseModel destinationDatabase = DestinationDatabaseModel.getInstance();

        travelPostData.setValues(userDatabase.getUsers(), travelDatabase.getTravels(),
                destinationDatabase.getDestinations());



        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton destination = findViewById(R.id.button_destination);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);
        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton home = findViewById(R.id.button_home);

        ImageButton addTravelPost = findViewById(R.id.addTravelPost);

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
                Intent intent = new Intent(TravelCommunityActivity.this,
                                            AccomodationsActivity.class);
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
        addTravelPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelCommunityActivity.this, AddTravelPostActivity.class);
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
