package com.example.sprint1_main.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.DestinationModel;

import java.util.ArrayList;

public class DestinationsAdapter extends RecyclerView.Adapter<DestinationsAdapter.MyViewHolder> {



    private Context context;



    private ArrayList<DestinationModel> destinationList;

    public DestinationsAdapter(Context context, ArrayList<DestinationModel> destinationList) {
        this.context = context;
        this.destinationList = destinationList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<DestinationModel> getDestinationList() {
        return destinationList;
    }

    public void setDestinationList(ArrayList<DestinationModel> destinationList) {
        this.destinationList = destinationList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.destination_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        DestinationModel destination = destinationList.get(position);
        holder.destinationName.setText(destination.getDestinationName());
        holder.startDate.setText("" + destination.getStartDate().getMonth() + "/"
                + destination.getStartDate().getDay() + "/"
                + destination.getStartDate().getYear());
        holder.endDate.setText("" + destination.getEndDate().getMonth() + "/"
                + destination.getEndDate().getDay() + "/"
                + destination.getEndDate().getYear());
        holder.duration.setText("" + destination.getEstimatedDays());

    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView getDestinationName() {
            return destinationName;
        }

        public void setDestinationName(TextView destinationName) {
            this.destinationName = destinationName;
        }

        private TextView destinationName;

        public TextView getStartDate() {
            return startDate;
        }

        public void setStartDate(TextView startDate) {
            this.startDate = startDate;
        }

        private TextView startDate;

        public TextView getEndDate() {
            return endDate;
        }

        public void setEndDate(TextView endDate) {
            this.endDate = endDate;
        }

        private TextView endDate;

        public TextView getDuration() {
            return duration;
        }

        public void setDuration(TextView duration) {
            this.duration = duration;
        }

        private TextView duration;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            destinationName = itemView.findViewById(R.id.destinationName);
            startDate = itemView.findViewById(R.id.startDate);
            endDate = itemView.findViewById(R.id.endDate);
            duration = itemView.findViewById(R.id.duration);
        }
    }
}
