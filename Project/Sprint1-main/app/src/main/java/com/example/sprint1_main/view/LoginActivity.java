package com.example.sprint1_main.view;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.util.Log;

import com.example.sprint1_main.R;
import com.example.sprint1_main.viewmodel.DestinationViewModel;
import com.example.sprint1_main.viewmodel.LoginViewModel;
import com.example.sprint1_main.model.UserModel;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        //creating temp user to communicate between view and viewmodel
        //(possibly be changed to global in the future to keep track of logged in user)
        UserModel tempUser = new UserModel("tempEmail", "tempNumber",
                "tempName", 0, "tempUsername", "tempPassword");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginViewModel.validateLogin(usernameInput, passwordInput, tempUser);
                DestinationViewModel.updateCurrentUser(tempUser);

                final android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (tempUser.getLoginStatus()) {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                }, 500);

            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "register onClick called");
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
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