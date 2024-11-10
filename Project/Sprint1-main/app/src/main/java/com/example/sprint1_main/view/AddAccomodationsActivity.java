package com.example.sprint1_main.view;

import static java.lang.Integer.parseInt;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.AccommodationDatabaseModel;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DateModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.LodgingModel;
import com.example.sprint1_main.viewmodel.LogisticsViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddAccomodationsActivity extends AppCompatActivity {
    private static final String TAG = "AddAccommodationsActivity";

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private String selectedRoomType;
    private String selectedRoomNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaccomodations);



        Spinner roomTypeSpinner = findViewById(R.id.roomType);
        Spinner roomNumSpinner = findViewById(R.id.roomNum);
        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);
        ImageButton community = findViewById(R.id.button_travelCommunity);
        ImageButton home = findViewById(R.id.button_home);
        Button add = findViewById(R.id.button_addAccomodation);
        EditText location = findViewById(R.id.location);
        EditText checkInField = findViewById(R.id.checkIn);
        EditText checkOutField = findViewById(R.id.checkOut);

        ArrayAdapter<CharSequence> roomTypeAdapter = ArrayAdapter.createFromResource(
                this, R.array.room_types, android.R.layout.simple_spinner_item);

        roomTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        roomTypeSpinner.setAdapter(roomTypeAdapter);

        ArrayAdapter<CharSequence> roomNumAdapter = ArrayAdapter.createFromResource(
                this, R.array.room_numbers, android.R.layout.simple_spinner_item);
        roomNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomNumSpinner.setAdapter(roomNumAdapter);


        roomTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRoomType = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO auto-generated
            }
        });

        roomNumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRoomNum = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO auto-generated
            }
        });


        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAccomodationsActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
        accommodations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAccomodationsActivity.this, AccomodationsActivity.class);
                startActivity(intent);
            }
        });
        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAccomodationsActivity.this, DiningActivity.class);
                startActivity(intent);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAccomodationsActivity.this, TravelCommunityActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAccomodationsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "add onClick called");






                String accommodationName = location.getText().toString();
                String checkInDate = checkInField.getText().toString().trim();
                String checkOutDate = checkOutField.getText().toString().trim();
                int roomNum = parseInt(selectedRoomNum);
                //MM/DD/YYYY
                String[] date1 = checkInDate.split("/");
                int m1 = parseInt(date1[0]);
                int d1 = parseInt(date1[1]);
                int y1 = parseInt(date1[2]);

                String[] date2 = checkOutDate.split("/");
                int m2 = parseInt(date2[0]);
                int d2 = parseInt(date2[1]);
                int y2 = parseInt(date2[2]);

                DateModel beginning = new DateModel(m1, d1, y1);
                DateModel ending = new DateModel(m2, d2, y2);

                LodgingModel accommodation = new LodgingModel(beginning, ending, roomNum, selectedRoomType, accommodationName);

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Accommodation Database");


                ApplicationManagerModel manager = ApplicationManagerModel.getInstance();
                if (manager.getCurrentDestination().getLodgings() == null) {
                    manager.getCurrentDestination().setLodgings(new ArrayList<>());
                }
                manager.getCurrentDestination().getLodgings().add(accommodation);

                AccommodationDatabaseModel accommodationManager = AccommodationDatabaseModel.getInstance();
                reference.child(accommodationName).setValue(accommodation);

                DatabaseReference ref2 = database.getReference("Destination Database");
                DatabaseReference ref3 = ref2.child(manager.getCurrentDestination().getDestinationName());
                ref3.child("lodgings").setValue(manager.getCurrentDestination().getLodgings());

                manager.updateUserDestinations();


                Intent intent = new Intent(AddAccomodationsActivity.this, AccomodationsActivity.class);
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
