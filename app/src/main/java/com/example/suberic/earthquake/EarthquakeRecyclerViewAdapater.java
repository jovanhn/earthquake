package com.example.suberic.earthquake;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.suberic.earthquake.databinding.ListItemEarthquakeBinding;

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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       ListItemEarthquakeBinding binding = ListItemEarthquakeBinding.inflate(
               LayoutInflater.from(viewGroup.getContext()),viewGroup,false);
       return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Earthquake earthquake = mEarthquakes.get(position);

        viewHolder.binding.setEarthquake(earthquake);
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mEarthquakes.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ListItemEarthquakeBinding binding;

        public ViewHolder(ListItemEarthquakeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setTimeformat(TIME_FORMAT);
            binding.setMagnitudeformat(MAGNITUDE_FORMAT);
        }
    }

}
