package com.example.sprint1_main.model;

import java.util.ArrayList;
import java.util.List;

public class TravelUpdater implements Observer {

    private TravelPostData travelPostData;

    public TravelUpdater(TravelPostData travelPostData) {
        this.travelPostData = travelPostData;
        travelPostData.registerObserver(this);
    }

    @Override
    public void update(List<UserModel> users, List<TravelModel> travels,
                       List<DestinationModel> destinations) {
        List<DestinationModel> updateDest = new ArrayList<>();

        if (destinations == null || destinations.size() == 0) {
            return;
        }

        for (TravelModel travel : travels) {
            for (DestinationModel d : travel.getDestinations()) {
                for (DestinationModel dest : destinations) {
                    if (dest.getDestinationName() != null
                            && dest.getDestinationName().equals(d.getDestinationName())) {
                        updateDest.add(dest);
                    }
                }
            }
            travel.setDestinations(updateDest);
        }
    }
}