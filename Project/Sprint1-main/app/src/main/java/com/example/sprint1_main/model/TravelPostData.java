package com.example.sprint1_main.model;

import java.util.ArrayList;
import java.util.List;

public class TravelPostData {
    private List<Observer> observers;
    private List<UserModel> users;
    private List<TravelModel> travels;
    private List<DestinationModel> destinations;


    public TravelPostData() {
        observers = new ArrayList<>();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update(users, travels, destinations);
        }
    }

    public void setValues(List<UserModel> users, List<TravelModel> travels,
                          List<DestinationModel> destinations) {
        this.users = users;
        this.travels = travels;
        this.destinations = destinations;
        notifyObservers();
    }
}
