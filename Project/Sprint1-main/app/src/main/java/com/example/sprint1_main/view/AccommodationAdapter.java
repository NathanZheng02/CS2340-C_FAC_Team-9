package com.example.sprint1_main.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.LodgingModel;

import java.util.ArrayList;

public class AccommodationAdapter extends RecyclerView.Adapter<AccommodationAdapter.MyViewHolder> {



    private Context context;



    private ArrayList<LodgingModel> lodgings;

    public AccommodationAdapter(Context context, ArrayList<LodgingModel> lodgings) {
        this.context = context;
        this.lodgings = lodgings;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<LodgingModel> getLodgings() {
        return lodgings;
    }

    public void setDestinationList(ArrayList<LodgingModel> lodgings) {
        this.lodgings = lodgings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.accommodation_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        LodgingModel lodging = lodgings.get(position);
        holder.location.setText(lodging.getLocation());
        holder.checkIn.setText("" + lodging.getCheckInTime().getMonth() + "/"
                + lodging.getCheckInTime().getDay() + "/"
                + lodging.getCheckInTime().getYear());
        holder.checkOut.setText("" + lodging.getCheckOutTime().getMonth() + "/"
                + lodging.getCheckOutTime().getDay() + "/"
                + lodging.getCheckOutTime().getYear());
        holder.roomType.setText(lodging.getRoomType());
        holder.roomNum.setText(lodging.getNumRooms());

    }

    @Override
    public int getItemCount() {
        return lodgings.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView getLocation() {
            return location;
        }

        public void setLocation(TextView location) {
            this.location = location;
        }

        private TextView location;

        public TextView getCheckIn() {
            return checkIn;
        }

        public void setCheckIn(TextView checkIn) {
            this.checkIn = checkIn;
        }

        private TextView checkIn;

        public TextView getCheckOut() {
            return checkOut;
        }

        public void setCheckOut(TextView checkOut) {
            this.checkOut = checkOut;
        }

        private TextView checkOut;

        public TextView getRoomType() {
            return roomType;
        }

        public void setRoomType(TextView roomType) {
            this.roomType = roomType;
        }

        private TextView roomType;

        private TextView roomNum;

        public TextView getRoomNum() {
            return roomNum;
        }

        public void setRoomNum(TextView roomNum) {
            this.roomNum = roomNum;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            location = itemView.findViewById(R.id.location);
            checkIn = itemView.findViewById(R.id.checkIn);
            checkOut = itemView.findViewById(R.id.checkOut);
            roomType = itemView.findViewById(R.id.roomType);
            roomNum = itemView.findViewById(R.id.numRooms);
        }
    }
}
