package com.example.sprint1_main.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private String email;
    private String phoneNumber;
    private String name;
    private int age;
    private String username;
    private String password;
    private boolean loginStatus;
    private List<DestinationModel> destinations;
    private int duration;


    public UserModel() {}



    public UserModel(String email, String phoneNumber, String name, int age,
                     String username, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.loginStatus = false;
        this.destinations = new ArrayList<>();
        this.duration = 0;

        //default destinations
        this.destinations.add(new DestinationModel("Paris", new DateModel(3, 10, 2009), new DateModel(4,10,2009)));
        this.destinations.add(new DestinationModel("Dublin", new DateModel(8, 16, 2015), new DateModel(8,20,2015)));
        this.destinations.add(new DestinationModel("New York", new DateModel(1, 1, 2023), new DateModel(2,30,2023)));
    }


    //user getters
    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getLoginStatus() {
        return loginStatus;
    }

    public List<DestinationModel> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationModel> destinations) {
        this.destinations = destinations;
    }



    //user setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}