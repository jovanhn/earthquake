package com.example.suberic.earthquake;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EarthquakeRecyclerViewAdapater extends RecyclerView.Adapter<EarthquakeRecyclerViewAdapater.ViewHolder>  {

    private static final SimpleDateFormat TIME_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);
    private static final NumberFormat MAGNITUDE_FORMAT =
            new DecimalFormat("0.0");

    private final List<Earthquake> mEarthquakes;

    public EarthquakeRecyclerViewAdapater(List<Earthquake> mEarthquakes) {
        this.mEarthquakes = mEarthquakes;
    }

    @Override
    public EarthquakeRecyclerViewAdapater.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_earthquake, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Earthquake earthquake = mEarthquakes.get(position);

        viewHolder.date.setText(TIME_FORMAT.format(earthquake.getDate()));
        viewHolder.details.setText(earthquake.getDetails());
        viewHolder.magnitude.setText(MAGNITUDE_FORMAT.format(earthquake.getMagnitude()));
    }

    @Override
    public int getItemCount() {
        return mEarthquakes.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView date;
        public final TextView details;
        public final TextView magnitude;

        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            details = (TextView) itemView.findViewById(R.id.details);
            magnitude = (TextView) itemView.findViewById(R.id.magnitude);
        }
    }

}
