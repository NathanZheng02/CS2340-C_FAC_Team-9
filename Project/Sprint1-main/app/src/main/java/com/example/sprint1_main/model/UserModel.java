package com.example.sprint1_main.model;

public class UserModel {
    private String email;
    private String phoneNumber;
    private String name;
    private int age;
    private String username;
    private String password;
    private boolean loginStatus;

    private DestinationModel destination;

    public UserModel(String email, String phoneNumber, String name, int age,
                     String username, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.loginStatus = false;
        destination = new DestinationModel();
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

    public vo