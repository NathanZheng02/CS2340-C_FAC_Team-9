package com.example.sprint1_main.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.example.sprint1_main.R;
import com.example.sprint1_main.databinding.ActivityMainBinding;
import com.example.sprint1_main.databinding.ActivityTravelcommunityBinding;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.LodgingModel;
import com.example.sprint1_main.model.ReservationModel;
import com.example.sprint1_main.model.TravelDatabaseModel;
import com.example.sprint1_main.model.TravelModel;

import java.util.ArrayList;
import java.util.List;

public class TravelCommunityActivity extends AppCompatActivity {

    private static final String TAG = "TravelCommunityActivity";

    ActivityTravelcommunityBinding binding;
    ArrayList<TravelModel> travelList;
    ArrayList<ReservationModel> dinings;
    ArrayList<LodgingModel> accommodations;
    ArrayList<DestinationModel> destinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        binding = ActivityTravelcommunityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TravelDatabaseModel travelDatabaseModel = TravelDatabaseModel.getInstance();

        travelList = new ArrayList<>();

        List<TravelModel> travels = travelDatabaseModel.getTravels();
        if (travels != null) {
            for (TravelModel travel: travels) {
                travelList.add(travel);
            }
        }

        TravelCommunityAdapter listAdapter = new TravelCommunityAdapter(TravelCommunityActivity.this, travelList);

        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TravelModel selectedTravel = travelList.get(position);
                Intent i = new Intent(TravelCommunityActivity.this, SpecificTravelPostActivity.class);
                i.putExtra("travelDetails", selectedTravel);
                startActivity(i);
            }
        });

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
