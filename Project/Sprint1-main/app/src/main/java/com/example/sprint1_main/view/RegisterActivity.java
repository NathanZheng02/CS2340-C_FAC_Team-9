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
    private static final String TAG = "RegisterActivity";

    //all variables for the user class
    private EditText registerUsername;
    private EditText registerName;
    private EditText registerAge;
    private EditText registerPhoneNumber;
    private EditText registerEmail;
    private EditText registerPassword;

    private Button registerButton;
    private Button backToLogin;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //information to be passed to firebase (only username and password for now)
        registerUsername = findViewById(R.id.register_usernameInput);
        registerPassword = findViewById(R.id.register_passwordInput);
        registerName = findViewById(R.id.name);
        registerAge = findViewById(R.id.age);
        registerPhoneNumber = findViewById(R.id.phoneNumber);
        registerEmail = findViewById(R.id.email);

        registerButton = findViewById(R.id.register_button);

        backToLogin = findViewById(R.id.loginPage);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String username = registerUsername.getText().toString();
                String password = registerPassword.getText().toString();
                String name = registerName.getText().toString();
                String age = registerAge.getText().toString();
                String phoneNumber = registerPhoneNumber.getText().toString();
                String email = registerEmail.getText().toString();

                UserModel user = new UserModel("email", "phone", "user y", 14, username, password);
                reference.child(username).setValue(user);

                Toast.makeText(RegisterActivity.this, "Registration successful!!",
                                Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
