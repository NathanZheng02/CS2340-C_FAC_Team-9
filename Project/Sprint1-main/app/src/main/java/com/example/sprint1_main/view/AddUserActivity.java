package com.example.sprint1_main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddUserActivity extends AppCompatActivity {

    private static final String TAG = "AddUserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);

        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        Button confirm = findViewById(R.id.confirm_user_Button);
        Button cancel = findViewById(R.id.user_return_logistics_Button);
        EditText userInput = findViewById(R.id.add_user_input);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userInput.getText().toString();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUserDatabase = reference.orderByChild("username").equalTo(username);

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel user = snapshot.child(username).getValue(UserModel.class);
                            //TODO: update firebase (both destination and user), add check to make sure user isn't double added
                            manager.getCurrentDestination().getContributingUsers().add(user);
                            Intent intent = new Intent(AddUserActivity.this, LogisticsActivity.class);
                            startActivity(intent);
                        } else {
                            userInput.setError("User Does Not Exist");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddUserActivity.this, LogisticsActivity.class);
                startActivity(intent);
            }
        });
    }
}
