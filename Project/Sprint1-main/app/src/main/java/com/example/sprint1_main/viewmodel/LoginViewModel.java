package com.example.sprint1_main.viewmodel;

import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginViewModel extends ViewModel {


    public LoginViewModel() {

    }

    public static void checkLoginInput(EditText usernameInput, EditText passwordInput) {


        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (username.isEmpty() || username == null) {
            usernameInput.setError("Please enter a username");
        }
        if (password.isEmpty() || password == null) {
            passwordInput.setError("Please enter a password");
        }
    }

    public static void validateLogin(EditText usernameInput, EditText passwordInput,
                                     ApplicationManagerModel manager) {
        LoginViewModel.checkLoginInput(usernameInput, passwordInput);

        String givenUsername = usernameInput.getText().toString().trim();
        String givenPassword = passwordInput.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(givenUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String correctPassword = snapshot.child(givenUsername).child("password")
                                                            .getValue(String.class);

                    if (correctPassword.equals(givenPassword)) {
                        //TODO: check if this works
                        UserModel user = snapshot.child(givenUsername).getValue(UserModel.class);
                        manager.setCurrentUser(user);
                        manager.getCurrentUser().setLoginStatus(true);
                    } else {
                        passwordInput.setError("Incorrect Password");
                    }
                } else {
                    usernameInput.setError("User Does Not Exist");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
