package com.example.sprint1_main.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sprint1_main.R;
import com.example.sprint1_main.model.ReservationModel;

import java.util.ArrayList;

public class DiningAdapter extends RecyclerView.Adapter<DiningAdapter.MyViewHolder> {



    private Context context;



    private ArrayList<ReservationModel> dinings;

    public DiningAdapter(Context context, ArrayList<ReservationModel> dinings) {
        this.context = context;
        this.dinings = dinings;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<ReservationModel> getDinings() {
        return dinings;
    }

    public void setDiningsList(ArrayList<ReservationModel> dinings) {
        this.dinings = dinings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dining_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ReservationModel dining = dinings.get(position);
        holder.location.setText(dining.getLocation());
        holder.date.setText("" + dining.getDate().getMonth() + "/"
                + dining.getDate().getDay() + "/"
                + dining.getDate().getYear());
        holder.time.setText("" + dining.getTime().getHour() + ":"
                + dining.getTime().getMinute());
        holder.website.setText("" + dining.getWebsite());
    }

    @Override
    public int getItemCount() {
        return dinings.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView getLocation() {
            return location;
        }

        public void setLocation(TextView location) {
            this.location = location;
        }

        private TextView location;

        public TextView getDate() {
            return date;
        }

        public void setDate(TextView date) {
            this.date = date;
        }

        private TextView date;

        public TextView getTime() {
            return time;
        }

        public void setTime(TextView time) {
            this.time = time;
        }

        private TextView time;

        public TextView getWebsite() {
            return website;
        }

        public void setWebsite(TextView website) {
            this.website = website;
        }

        private TextView website;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            location = itemView.findViewById(R.id.diningLocation);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            website = itemView.findViewById(R.id.website);
        }
    }
}
