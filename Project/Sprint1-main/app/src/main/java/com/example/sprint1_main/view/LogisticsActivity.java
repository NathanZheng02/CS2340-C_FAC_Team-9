package com.example.sprint1_main.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DateModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.UserModel;
import com.example.sprint1_main.viewmodel.LogisticsViewModel;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class LogisticsActivity extends AppCompatActivity {

    private static final String TAG = "LogisticsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);

        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();
        DestinationModel currentDestination = new DestinationModel("Thailand", new DateModel(5, 22, 2001), new DateModel(6,1,2001));
        currentDestination.getContributingUsers().add(manager.getCurrentUser());


        if (manager.getCurrentUser().getDestinations() != null) {
            manager.getCurrentUser().getDestinations().add(currentDestination);
        } else {
            manager.getCurrentUser().setDestinations(new ArrayList<>());
            manager.getCurrentUser().getDestinations().add(currentDestination);
        }

        manager.setCurrentDestination(currentDestination);


        TextView notes = findViewById(R.id.notes_body);
        TextView contributers = findViewById(R.id.contributer_body);

        Spinner destinations_spinner = (Spinner) findViewById(R.id.destinations_spinner);

        List<String> destination_names = new ArrayList<>();
        for (DestinationModel destination : manager.getCurrentUser().getDestinations()) {
            destination_names.add(destination.getDestinationName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, destination_names);

        destinations_spinner.setAdapter(adapter);

        destinations_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("destination name", (String) parent.getItemAtPosition(position));

                for (DestinationModel destination : manager.getCurrentUser().getDestinations()) {
                    if (destination.getDestinationName().equals((String) parent.getItemAtPosition(position))) {
                        manager.setCurrentDestination(destination);
                        LogisticsViewModel.updateNotes(notes);
                        LogisticsViewModel.updateUsers(contributers);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO auto-generated
            }
        });


        LogisticsViewModel.updateNotes(notes);
        LogisticsViewModel.updateUsers(contributers);



        Button addNote = findViewById(R.id.add_note_button);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogisticsActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });


        Button addUser = findViewById(R.id.add_user_button);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogisticsActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });










        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton destination = findViewById(R.id.button_destination);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);
        ImageButton community = findViewById(R.id.button_travelCommunity);
        ImageButton home = findViewById(R.id.button_home);

        accommodations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogisticsActivity.this, AccomodationsActivity.class);
                startActivity(intent);
            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticsActivity.this, DestinationActivity.class);
                startActivity(intent);
            }
        });
        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticsActivity.this, DiningActivity.class);
                startActivity(intent);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticsActivity.this, TravelCommunityActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticsActivity.this, HomeActivity.class);
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
