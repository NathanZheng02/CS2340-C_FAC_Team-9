package com.example.sprint1_main.view;

import static java.lang.Integer.parseInt;

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
import com.example.sprint1_main.model.TravelModel;
import com.example.sprint1_main.model.UserDatabaseModel;
import com.example.sprint1_main.viewmodel.LogisticsViewModel;
import com.example.sprint1_main.viewmodel.TravelPostViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddTravelPostActivity extends AppCompatActivity  {
    private static final String TAG = "AddTravelPostActivity";

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);


        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();
        UserDatabaseModel userDatabase = UserDatabaseModel.getInstance();
        DestinationDatabaseModel destinationDatabase = DestinationDatabaseModel.getInstance();

        manager.updateUserDestinations();

        if (manager.getCurrentUser().getDestinations() == null) {
            manager.getCurrentUser().setDestinations(new ArrayList<>());
        }

        if (manager.getCurrentUser().getDestinations().size() >= 1) {
            manager.setCurrentDestination(manager.getCurrentUser().getDestinations().get(0));
        }


        TextView accommodationText = findViewById(R.id.accomodations);
        TextView diningText = findViewById(R.id.diningreservations);
        Button addPost = findViewById(R.id.button_addTravelPost);

        EditText startField = findViewById(R.id.starting);
        EditText endField = findViewById(R.id.ending);
        EditText noteField = findViewById(R.id.notesAboutTrip);

        Spinner destinationsSpinner = (Spinner) findViewById(R.id.travel_destination_spinner);


        List<String> dN = new ArrayList<>();
        for (DestinationModel destination : manager.getCurrentUser().getDestinations()) {
            dN.add(destination.getDestinationName());
        }

        ArrayAdapter<String> a = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dN);

        destinationsSpinner.setAdapter(a);

        //TODO: change spinner for textbox lookup (see adduseractivity)

        destinationsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("destination name", (String) parent.getItemAtPosition(position));

                for (DestinationModel d : manager.getCurrentUser().getDestinations()) {
                    String check = (String) parent.getItemAtPosition(position);
                    if (d.getDestinationName().equals(check)) {
                        manager.setCurrentDestination(d);
                        TravelPostViewModel.updateAccommodations(accommodationText);
                        TravelPostViewModel.updateDining(diningText);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO auto-generated
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

                TravelModel travelPost = new TravelModel(manager.getCurrentUser(), start, end);
                if (!note.equals("")) {
                    travelPost.getNotes().add(note);
                }

                //TODO: finish firebase connection

//                database = FirebaseDatabase.getInstance();
//                reference = database.getReference("Travel Post Database");

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
