package com.example.sprint1_main.viewmodel;

import android.widget.TextView;

import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.LodgingModel;
import com.example.sprint1_main.model.ReservationModel;

public class TravelPostViewModel {

    TravelPostViewModel() {

    }

    public static void updateAccommodations(TextView accommodations) {
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        StringBuilder accomBuilder = new StringBuilder();
        accomBuilder.append("Accommodations:");
        if (manager.getCurrentTravel().getDestinations() != null) {
            for (DestinationModel destination : manager.getCurrentTravel().getDestinations()) {
                for (LodgingModel lodging : destination.getLodgings()) {
                    accomBuilder.append("\n");
                    accomBuilder.append("Location: ");
                    accomBuilder.append(lodging.getLocation());
                }
            }
        }

        accommodations.setText(accomBuilder.toString());

    }


    public static void updateDining(TextView dining) {
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        StringBuilder diningBuilder = new StringBuilder();
        diningBuilder.append("Dining Reservations:");
        if (manager.getCurrentTravel().getDestinations() != null) {
            for (DestinationModel destination : manager.getCurrentTravel().getDestinations()) {
                for (ReservationModel reservation : destination.getReservations()) {
                    diningBuilder.append("\n");
                    diningBuilder.append("Location: ");
                    diningBuilder.append(reservation.getLocation());
                }
            }
        }

        dining.setText(diningBuilder.toString());

    }


}
