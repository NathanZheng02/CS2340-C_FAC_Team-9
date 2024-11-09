package com.example.sprint1_main.view;

import static java.lang.Integer.parseInt;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DateModel;
import com.example.sprint1_main.model.DestinationModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAccomodationsActivity extends AppCompatActivity {
    private static final String TAG = "AddAccomodationsActivity";

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaccomodations);

        ImageButton logistics = findViewById(R.id.button_logistics);
        ImageButton accommodations = findViewById(R.id.button_accommodations);
        ImageButton dining = findViewById(R.id.button_diningEstablishments);
        ImageButton community = findViewById(R.id.button_travelCommunity);
        ImageButton home = findViewById(R.id.button_home);
        Button add = findViewById(R.id.button_addAccomodation);
        EditText location = findViewById(R.id.location);
        EditText checkInField = findViewById(R.id.checkIn);
        EditText checkOutField = findViewById(R.id.checkOut);
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
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Accomodation Database");



                String accomodationName = location.getText().toString();
                String checkInDate = checkInField.getText().toString().trim();
                String checkOutDate = checkOutField.getText().toString().trim();
                //MM/DD/YYYY
                int m1 = parseInt(checkInDate.substring(0, 2));
                int d1 = parseInt(checkInDate.substring(3, 5));
                int y1 = parseInt(checkInDate.substring(6));

                int m2 = parseInt(checkOutDate.substring(0, 2));
                int d2 = parseInt(checkOutDate.substring(3, 5));
                int y2 = parseInt(checkOutDate.substring(6));

                DateModel beginning = new DateModel(m1, d1, y1);
                DateModel ending = new DateModel(m2, d2, y2);

                DestinationModel dest = new DestinationModel(accomodationName, beginning, ending);

                reference.child(accomodationName).setValue(dest);




                ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

                manager.getCurrentUser().getDestinations().add(dest);

                DatabaseReference r1 =
                        FirebaseDatabase.getInstance().getReference("User Database");
                DatabaseReference r2 =
                        r1.child(manager.getCurrentUser().getUsername());
                DatabaseReference r3 = r2.child("destinations");
                r3.child("" + manager.getCurrentUser().getDestinations().size()).setValue(dest);

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
