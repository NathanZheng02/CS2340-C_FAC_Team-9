package com.example.sprint1_main.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ApplicationManagerModel;
import com.example.sprint1_main.model.DestinationModel;
import com.example.sprint1_main.model.TravelDatabaseModel;
import com.example.sprint1_main.model.TravelModel;
import com.example.sprint1_main.model.UserModel;

import java.util.ArrayList;

public class TravelCommunityAdapter extends ArrayAdapter<TravelModel> {


    public TravelCommunityAdapter(Context context, ArrayList<TravelModel> travelArrayList) {
        super(context, R.layout.list_item,travelArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TravelModel travel = getItem(position);
        String destinations = "";

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        TextView userName = convertView.findViewById(R.id.username);
        TextView dest = convertView.findViewById(R.id.destination);
        TextView duration = convertView.findViewById(R.id.duration);

        userName.setText(travel.getUsers().toString());
        for (DestinationModel destination: travel.getDestinations()) {
            destinations += destination.getDestinationName();
        }
        dest.setText(destinations);
        duration.setText("" + travel.getDuration());

        return convertView;
    }
}
