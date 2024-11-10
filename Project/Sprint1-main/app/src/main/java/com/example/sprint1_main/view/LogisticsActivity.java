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
import com.example.sprint1_main.model.DestinationDatabaseModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.UserDatabaseModel;
import com.example.sprint1_main.viewmodel.LogisticsViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class LogisticsActivity extends AppCompatActivity {

    private static final String TAG = "LogisticsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);

        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();
        UserDatabaseModel userDatabase = UserDatabaseModel.getInstance();
        DestinationDatabaseModel destinationDatabase = DestinationDatabaseModel.getInstance();

        manager.updateUserDestinations();

        if (manager.getCurrentUser().getDestinations().size() >= 1) {
            manager.setCurrentDestination(manager.getCurrentUser().getDestinations().get(0));
        }



        TextView notes = findViewById(R.id.notes_body);
        TextView contributers = findViewById(R.id.contributer_body);
        TextView totalDays = findViewById(R.id.total_days_text);

        Spinner destinationsSpinner = (Spinner) findViewById(R.id.destinations_spinner);

//        LogisticsViewModel.updateDestinations();
        List<String> dN = new ArrayList<>();
        for (DestinationModel destination : manager.getCurrentUser().getDestinations()) {
            dN.add(destination.getDestinationName());
        }

        ArrayAdapter<String> a = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dN);

        destinationsSpinner.setAdapter(a);

        destinationsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("destination name", (String) parent.getItemAtPosition(position));

                for (DestinationModel d : manager.getCurrentUser().getDestinations()) {
                    String check = (String) parent.getItemAtPosition(position);
                    if (d.getDestinationName().equals(check)) {
                        manager.setCurrentDestination(d);
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


        if (manager.getCurrentDestination() != null) {
            LogisticsViewModel.updateNotes(notes);
            LogisticsViewModel.updateUsers(contributers);
            LogisticsViewModel.updateDays(totalDays);
        }




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


        //find the chart creation button to create the graph
        Button graphButton = findViewById(R.id.graph_button);
        //Draw a bar chart when clicked
        graphButton.setOnClickListener((l) -> drawGraph(manager.getCurrentUser().getDuration(),
                LogisticsViewModel.getDays()));










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

    public void drawGraph(int allottedTime, int plannedTime) {
        //prepare the data for the chart
        List<BarEntry> valuesAllotted = new ArrayList<>();
        List<BarEntry> valuesPlanned = new ArrayList<>();

        valuesAllotted.add(new BarEntry(0f, allottedTime));
        valuesPlanned.add(new BarEntry(2f, plannedTime));

        //create a bardataset from the data entries

        BarDataSet setAllotted = new BarDataSet(valuesAllotted, "Allotted Time");
        BarDataSet setPlanned = new BarDataSet(valuesPlanned, "Planned Time");


        BarChart barChart = findViewById(R.id.barChart);
        BarData data = new BarData(setAllotted, setPlanned);
        barChart.setData(data);

        //optional chart customization
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false); //hiding the right axis
        barChart.getDescription().setEnabled(false); //hiding the description label

        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);

        //redraw the chart
        barChart.invalidate(); //refresh the chart
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
