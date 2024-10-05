package com.example.sprint1_main.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.UserModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private final String TAG = "RegisterActivity";

    //all variables for the user class
    EditText register_username, register_password, register_name, register_age, register_phone_number, register_email;

    Button register_button, backToLogin;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //information to be passed to firebase (only username and password for now)
        //TODO: add other user variables
        register_username = findViewById(R.id.register_usernameInput);
        register_password = findViewById(R.id.register_passwordInput);
        register_name = findViewById(R.id.name);
        register_age = findViewById(R.id.age);
        register_phone_number = findViewById(R.id.phoneNumber);
        register_email = findViewById(R.id.email);

        register_button = findViewById(R.id.register_button);

        backToLogin = findViewById(R.id.loginPage);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                //TODO: add other user variables + text boxes
                String username = register_username.getText().toString();
                String password = register_password.getText().toString();
                String name = register_name.getText().toString();
                String age = register_age.getText().toString();
                String phone_number = register_phone_number.getText().toString();
                String email = register_email.getText().toString();

                UserModel user = new UserModel("email", "phone", "user y",14, username, password);
                reference.child(username).setValue(user);

                //TODO: add feedback to confirm registration success
                Toast.makeText(RegisterActivity.this, "Registration successful!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //TODO: add button to redirect back to login
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
