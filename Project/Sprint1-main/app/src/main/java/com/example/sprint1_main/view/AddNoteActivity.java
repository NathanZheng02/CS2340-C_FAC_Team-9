package com.example.sprint1_main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DestinationDatabaseModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.UserDatabaseModel;
import com.example.sprint1_main.model.UserModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    private static final String TAG = "AddNoteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();
        UserDatabaseModel userDatabase = UserDatabaseModel.getInstance();
        DestinationDatabaseModel destinationDatabaseModel = DestinationDatabaseModel.getInstance();

        Button confirm = findViewById(R.id.enter_note_Button);
        Button cancel = findViewById(R.id.note_return_logistics_Button);
        EditText noteInput = findViewById(R.id.note_input);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = noteInput.getText().toString();

                if (manager.getCurrentDestination().getNotes() == null) {
                    manager.getCurrentDestination().setNotes(new ArrayList<>());
                }
                manager.getCurrentDestination().getNotes().add(note);

                DatabaseReference reference =
                        FirebaseDatabase.getInstance().getReference("Destination Database");
                DatabaseReference reference2 =
                        reference.child(manager.getCurrentDestination().getDestinationName());

                reference2.child("notes").setValue(manager.getCurrentDestination().getNotes());

                DatabaseReference reference3 =
                        FirebaseDatabase.getInstance().getReference("User Database");
                DatabaseReference reference4 =
                        reference3.child(manager.getCurrentUser().getUsername());
                DatabaseReference reference5 = reference4.child("destinations");
                UserModel currUser = manager.getCurrentUser();
                List<DestinationModel> currDestinations = currUser.getDestinations();
                int index = currDestinations.indexOf(manager.getCurrentDestination());
                DatabaseReference reference6 =
                        reference5.child("" + index);
                reference6.child("notes").setValue(manager.getCurrentDestination().getNotes());


                Intent intent = new Intent(AddNoteActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNoteActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
    }
}
