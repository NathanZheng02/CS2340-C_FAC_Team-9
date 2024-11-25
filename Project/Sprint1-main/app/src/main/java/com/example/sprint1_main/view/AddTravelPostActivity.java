package com.example.sprint1_main.view;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DateModel;
import com.example.sprint1_main.model.DestinationDatabaseModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.TravelDatabaseModel;
import com.example.sprint1_main.model.TravelModel;
import com.example.sprint1_main.model.UserDatabaseModel;
import com.example.sprint1_main.viewmodel.LogisticsViewModel;
import com.example.sprint1_main.viewmodel.TravelPostViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddTravelPostActivity extends AppCompatActivity  {
    private static final String TAG = "AddTravelPostActivity";

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private String selectedTransportation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);


        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();
        UserDatabaseModel userDatabase = UserDatabaseModel.getInstance();
        DestinationDatabaseModel destinationDatabase = DestinationDatabaseModel.getInstance();

        manager.updateUserDestinations();

        manager.setCurrentTravel(new TravelModel(manager.getCurrentUser(), new DateModel(0,0,0), new DateModel(0,0,0)));
        manager.getCurrentTravel().setDestinations(new ArrayList<>());


        Spinner transportationSpinner = findViewById(R.id.transportation);
        TextView accommodationText = findViewById(R.id.accomodations);
        TextView diningText = findViewById(R.id.diningreservations);
        Button addPost = findViewById(R.id.button_addTravelPost);
        Button addDestination = findViewById(R.id.button_addDestination);

        EditText startField = findViewById(R.id.starting);
        EditText endField = findViewById(R.id.ending);
        EditText noteField = findViewById(R.id.notesAboutTrip);
        EditText destInput = findViewById(R.id.destination_input);



        ArrayAdapter<CharSequence> transportationAdapter = ArrayAdapter.createFromResource(
                this, R.array.transportation_type, android.R.layout.simple_spinner_item);
        transportationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transportationSpinner.setAdapter(transportationAdapter);

        transportationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTransportation = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO auto-generated
            }
        });


        addDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String destName = destInput.getText().toString();

                FirebaseDatabase fb = FirebaseDatabase.getInstance();
                DatabaseReference reference = fb.getReference("Destination Database");

                Query checkUserDatabase = reference.orderByChild("destinationName").equalTo(destName);

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            DestinationModel destination = snapshot.child(destName).getValue(DestinationModel.class);

                            boolean hasDest = false;

                            for (DestinationModel dest : manager.getCurrentUser().getDestinations()) {
                                if (dest.getDestinationName().equals(destName)) {
                                    manager.getCurrentTravel().getDestinations().add(destination);

                                    TravelPostViewModel.updateAccommodations(accommodationText);
                                    TravelPostViewModel.updateDining(diningText);
                                    hasDest = true;
                                }
                            }

                            if (!hasDest) {
                                destInput.setError("Destination Does Not Belong To User");
                            }


                        } else {
                            destInput.setError("Destination Does Not Exist");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Add Travel Post onClick Called");

                String startText = startField.getText().toString().trim();
                String endText = endField.getText().toString().trim();
                String note = noteField.getText().toString();



                String[] startArr = startText.split("/");
                if (startArr.length != 3) {
                    startField.setError("Date Must Be In Format MM/DD/YYYY");
                    return;
                }
                int m1 = parseInt(startArr[0]);
                int d1 = parseInt(startArr[1]);
                int y1 = parseInt(startArr[2]);

                String[] endArr = endText.split("/");
                if (endArr.length != 3) {
                    endField.setError("Date Must Be In Format MM/DD/YYYY");
                    return;
                }
                int m2 = parseInt(endArr[0]);
                int d2 = parseInt(endArr[1]);
                int y2 = parseInt(endArr[2]);

                DateModel start = new DateModel(m1, d1, y1);
                DateModel end = new DateModel(m2, d2, y2);

                manager.getCurrentTravel().setStartDate(start);
                manager.getCurrentTravel().setEndDate(end);
                if (!note.equals("")) {
                    manager.getCurrentTravel().getNotes().add(note);
                }

                TravelDatabaseModel travelDatabase = TravelDatabaseModel.getInstance();

                TravelModel travel = manager.getCurrentTravel();

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Travel Post Database");

                reference.child("" + travelDatabase.getTravels().size()).setValue(travel);

                Intent i = new Intent(AddTravelPostActivity.this, TravelCommunityActivity.class);
                startActivity(i);
            }
        });




        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton destination = findViewById(R.id.button_destination);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);
        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton home = findViewById(R.id.button_home);


        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTravelPostActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTravelPostActivity.this, DestinationActivity.class);
                startActivity(intent);
            }
        });
        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTravelPostActivity.this, DiningActivity.class);
                startActivity(intent);
            }
        });
        accommodations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTravelPostActivity.this,
                        AccomodationsActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTravelPostActivity.this, HomeActivity.class);
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
