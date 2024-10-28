package com.example.sprint1_main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNoteActivity extends AppCompatActivity {

    private static final String TAG = "AddNoteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        Button confirm = findViewById(R.id.enter_note_Button);
        Button cancel = findViewById(R.id.note_return_logistics_Button);
        EditText noteInput = findViewById(R.id.note_input);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: update in firebase
                String note = noteInput.getText().toString();

                manager.getCurrentDestination().getNotes().add(note);

//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("destinations");
//                reference.child(manager.getCurrentDestination()).child("notes").setValue(manager.getCurrentDestination().getNotes());

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
