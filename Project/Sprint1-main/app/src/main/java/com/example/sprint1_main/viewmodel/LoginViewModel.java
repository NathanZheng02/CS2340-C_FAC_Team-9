package com.example.sprint1_main.viewmodel;

import android.widget.EditText;

import androidx.lifecycle.ViewModel;

import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.UserDatabaseModel;
import com.example.sprint1_main.model.UserModel;

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


        UserDatabaseModel userDatabase = UserDatabaseModel.getInstance();


        for (UserModel user : userDatabase.getUsers()) {
            if (user.getUsername().equals(givenUsername)) {
                if (user.getPassword().equals(givenPassword)) {
                    user.setLoginStatus(true);
                    manager.setCurrentUser(user);
                } else {
                    passwordInput.setError("Incorrect Password");
                }
            } else {
                usernameInput.setError("User Does Not Exist");
            }
        }


    }
}
