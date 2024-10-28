package com.example.sprint1_main.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.DestinationModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DestinationActivity extends AppCompatActivity {

    private static final String TAG = "DestinationActivity";

    RecyclerView recyclerView;
    DatabaseReference database;
    Adapter adapter;
    ArrayList<DestinationModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);
        ImageButton community = findViewById(R.id.button_travelCommunity);
        ImageButton home = findViewById(R.id.button_home);
        Button logTravel = findViewById(R.id.button_logTravel);
        Button vacationTime = findViewById(R.id.button_vacationTime);

        recyclerView = findViewById(R.id.destinationList);
        database = FirebaseDatabase.getInstance().getReference("Destination Database");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new Adapter(this,list);
        recyclerView.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapShot : snapshot.getChildren()) {

                    DestinationModel destination = dataSnapShot.getValue(DestinationModel.class);
                    list.add(destination);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DestinationActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
        accommodations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationActivity.this, AccomodationsActivity.class);
                startActivity(intent);
            }
        });
        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationActivity.this, DiningActivity.class);
                startActivity(intent);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationActivity.this, TravelCommunityActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        logTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationActivity.this, LogTravelActivity.class);
                startActivity(intent);
            }
        });
        vacationTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationActivity.this, CalculateVacationTimeActivity.class);
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
