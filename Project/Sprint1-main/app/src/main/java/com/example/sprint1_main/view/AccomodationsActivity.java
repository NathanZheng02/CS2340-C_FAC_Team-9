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
import com.example.sprint1_main.model.CheckInSortStrategy;
import com.example.sprint1_main.model.CheckOutSortStrategy;
import com.example.sprint1_main.model.Context;
import com.example.sprint1_main.model.LodgingModel;
import com.example.sprint1_main.model.Sortable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class AccomodationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference database;
    private AccommodationAdapter adapter;
    private ArrayList<LodgingModel> list;

    private static final String TAG = "AccomodationsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomodations);

        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton destination = findViewById(R.id.button_destination);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);
        ImageButton community = findViewById(R.id.button_travelCommunity);
        ImageButton home = findViewById(R.id.button_home);

        Button checkInSort = findViewById(R.id.button_sortByCheckIn);
        Button checkOutSort = findViewById(R.id.button_sortByCheckOut);

        FloatingActionButton addAccommodation = findViewById(R.id.addAccommodation);

        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        if (manager.getCurrentDestination() != null
                && manager.getCurrentDestination().getLodgings().size() > 0) {
            list = new ArrayList<>();

            List<LodgingModel> lodgingsList = manager.getCurrentDestination().getLodgings();
            for (LodgingModel lodging: lodgingsList) {
                list.add(lodging);
            }

            recyclerView = findViewById(R.id.accommodationList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter = new AccommodationAdapter(this, list);
            recyclerView.setAdapter(adapter);
        }





        checkInSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

                List<Sortable> unsorted = new ArrayList<>();

                for (LodgingModel lodging: manager.getCurrentDestination().getLodgings()) {
                    unsorted.add((Sortable) lodging);
                }


                Context context = new Context();

                context.setStrategy(new CheckInSortStrategy());

                List<Sortable> sorted = context.executeStrategy(unsorted);

                ArrayList<LodgingModel> newLodgings = new ArrayList<>();
                for (Sortable sortItem : sorted) {
                    newLodgings.add((LodgingModel) sortItem);
                }

                adapter = new AccommodationAdapter(AccomodationsActivity.this, newLodgings);
                recyclerView.setAdapter(adapter);

            }
        });


        checkOutSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

                List<Sortable> unsorted = new ArrayList<>();

                for (LodgingModel lodging: manager.getCurrentDestination().getLodgings()) {
                    unsorted.add((Sortable) lodging);
                }


                Context context = new Context();

                context.setStrategy(new CheckOutSortStrategy());

                List<Sortable> sorted = context.executeStrategy(unsorted);

                ArrayList<LodgingModel> newLodgings = new ArrayList<>();
                for (Sortable sortItem : sorted) {
                    newLodgings.add((LodgingModel) sortItem);
                }

                adapter = new AccommodationAdapter(AccomodationsActivity.this, newLodgings);
                recyclerView.setAdapter(adapter);

            }
        });




        logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccomodationsActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccomodationsActivity.this, DestinationActivity.class);
                startActivity(intent);
            }
        });
        dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccomodationsActivity.this, DiningActivity.class);
                startActivity(intent);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccomodationsActivity.this,
                                            TravelCommunityActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccomodationsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        addAccommodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccomodationsActivity.this,
                        AddAccomodationsActivity.class);
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
