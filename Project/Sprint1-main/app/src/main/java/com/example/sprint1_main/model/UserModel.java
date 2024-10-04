package com.example.sprint1_main.model;

public class UserModel {
    private int birthdate;
    private String contactInformation;
    private String name;
    private int age;
    private String username;
    private String password;

    public UserModel(int birthdate, String contactInformation, String name, int age, String username, String password) {
        this.birthdate = birthdate;
        this.contactInformation = contactInformation;
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    //user getters
    public int getBirthdate() {
        return birthdate;
    }

    public String getContactInformation() {
        return contactInformation;
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


    //user setters
    public void setBirthdate(int birthdate) {
        this.birthdate = birthdate;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
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
}
