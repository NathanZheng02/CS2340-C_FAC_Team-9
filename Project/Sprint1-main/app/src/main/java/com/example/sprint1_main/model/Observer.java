package com.example.sprint1_main.model;

import java.util.List;

public interface Observer {
    public void update(List<UserModel> users, List<TravelModel> travels,
                       List<DestinationModel> destinations);
}
