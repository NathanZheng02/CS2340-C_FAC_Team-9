package com.example.sprint1_main;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.sprint1_main.model.UserModel;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void userModelAccess() {
        //First User Test with getters
        UserModel newUser = new UserModel("UserName@email.com", "123-456-7890", "UserName", 18, "goodUserName", "goodPassword");

        assertEquals(newUser.getName(), "UserName");
        assertEquals(newUser.getEmail(), "UserName@email.com");

        //Second User Test with setters
        UserModel jonDoe = new UserModel("johnDoe@email.com", "123-456-7890", "John Doe", 25, "JohnDoe14", "secretAccountPass14!");
        jonDoe.setName("Jon Doe");
        jonDoe.setEmail("jonDoe@gmail.com");
        jonDoe.setUsername("JonDoe12");

        assertEquals(jonDoe.getName(), "Jon Doe");
        assertEquals(jonDoe.getEmail(), "jonDoe@gmail.com");
        assertEquals(jonDoe.getUsername(), "JonDoe12");
    }
}