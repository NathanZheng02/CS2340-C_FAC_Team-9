package com.example.sprint1_main.viewmodel;

import android.widget.TextView;

import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.LodgingModel;
import com.example.sprint1_main.model.ReservationModel;

public class TravelPostViewModel {

    TravelPostViewModel() {

    }

    public static void updateAccommodations(TextView accommodations) {
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        StringBuilder accomBuilder = new StringBuilder();
        accomBuilder.append("Accommodations:");
        if (manager.getCurrentDestination().getLodgings() != null) {
            for (LodgingModel lodging : manager.getCurrentDestination().getLodgings()) {
                accomBuilder.append("\n");
                accomBuilder.append("Location: ");
                accomBuilder.append(lodging.getLocation());
                accomBuilder.append(", Check In: ");
                accomBuilder.append(lodging.getCheckInTime());
                accomBuilder.append(", Check Out: ");
                accomBuilder.append(lodging.getCheckOutTime());
                accomBuilder.append(", Rooms: ");
                accomBuilder.append(lodging.getNumRooms());
                accomBuilder.append(", Room Type: ");
                accomBuilder.append(lodging.getRoomType());
            }
        }

        accommodations.setText(accomBuilder.toString());

    }


    public static void updateDining(TextView dining) {
        ApplicationManagerModel manager = ApplicationManagerModel.getInstance();

        StringBuilder diningBuilder = new StringBuilder();
        diningBuilder.append("Accommodations:");
        if (manager.getCurrentDestination().getReservations() != null) {
            for (ReservationModel reservation : manager.getCurrentDestination().getReservations()) {
                diningBuilder.append("\n");
                diningBuilder.append("Location: ");
                diningBuilder.append(reservation.getLocation());
                diningBuilder.append(", Date: ");
                diningBuilder.append(reservation.getDate());
                diningBuilder.append(", Time: ");
                diningBuilder.append(reservation.getTime());
                diningBuilder.append(", Website: ");
                diningBuilder.append(reservation.getWebsite());
            }
        }

        dining.setText(diningBuilder.toString());

    }


}
