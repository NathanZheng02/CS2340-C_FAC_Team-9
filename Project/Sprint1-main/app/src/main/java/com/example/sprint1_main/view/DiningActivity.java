package com.example.sprint1_main.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.Context;
import com.example.sprint1_main.model.ResDateSortStrategy;
import com.example.sprint1_main.model.ResTimeSortStrategy;
import com.example.sprint1_main.model.ReservationModel;
import com.example.sprint1_main.model.Sortable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DiningActivity extends AppCompatActivity {

    private static final String TAG = "DiningActivity";
    private RecyclerView recyclerView;
    private DiningAdapter adapter;
    private ArrayList<ReservationModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining);

        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton destination = findViewById(R.id.button_destination);
        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton community = findViewById(R.id.button_travelCommunity);
        ImageButton home = findViewById(R.id.button_home);
        FloatingActionButton addDining = findViewById(R.id.addDining);

        Button dateSort = findViewById(R.id.button_sortByDate);
        Button timeSort = findViewById(R.id.button_sortByTime);

        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();


        if (manager.getCurrentDestination() != null
                && manager.getCurrentDestination().getReservations() != null) {
            list = new ArrayList<>();

            List<ReservationModel> resList = manager.getCurrentDestination().getReservations();
            for (ReservationModel reservation: resList) {
                list.add(reservation);
            }

            recyclerView = findViewById(R.id.diningList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter = new DiningAdapter(this, list);
            recyclerView.setAdapter(adapter);
        }



        dateSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

                List<Sortable> unsorted = new ArrayList<>();

                for (ReservationModel res: manager.getCurrentDestination().getReservations()) {
                    unsorted.add((Sortable) res);
                }


                Context context = new Context();

                context.setStrategy(new ResDateSortStrategy());

                List<Sortable> sorted = context.executeStrategy(unsorted);

                ArrayList<ReservationModel> newReservations = new ArrayList<>();
                for (Sortable sortItem : sorted) {
                    newReservations.add((ReservationModel) sortItem);
                }

                adapter = new DiningAdapter(DiningActivity.this, newReservations);
                recyclerView.setAdapter(adapter);
            }
        });


        timeSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

                List<Sortable> unsorted = new ArrayList<>();

                for (ReservationModel res: manager.getCurrentDestination().getReservations()) {
                    unsorted.add((Sortable) res);
                }


                Context context = new Context();

                context.setStrategy(new ResTimeSortStrategy());

                List<Sortable> sorted = context.executeStrategy(unsorted);

                ArrayList<ReservationModel> newReservations = new ArrayList<>();
                for (Sortable sortItem : sorted) {
                    newReservations.add((ReservationModel) sortItem);
                }

                adapter = new DiningAdapter(DiningActivity.this, newReservations);
                recyclerView.setAdapter(adapter);
            }
        });


        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiningActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiningActivity.this, DestinationActivity.class);
                startActivity(intent);
            }
        });
        accommodations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiningActivity.this, AccomodationsActivity.class);
                startActivity(intent);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiningActivity.this, TravelCommunityActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiningActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        addDining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiningActivity.this, AddDiningActivity.class);
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
