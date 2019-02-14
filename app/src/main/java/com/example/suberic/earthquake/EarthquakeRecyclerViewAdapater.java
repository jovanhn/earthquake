package com.example.suberic.earthquake;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EarthquakeRecyclerViewAdapater extends RecyclerView.Adapter<EarthquakeRecyclerViewAdapater.ViewHolder>  {

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
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.earthquake = mEarthquakes.get(i);
        viewHolder.detailsView.setText(mEarthquakes.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return mEarthquakes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View parentView;
        public final TextView detailsView;
        public Earthquake earthquake;

        public ViewHolder(View itemView) {
            super(itemView);
            parentView = itemView;
            detailsView = (TextView) itemView.findViewById(R.id.list_item_earthquake_details);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + detailsView.getText() + "'";
        }
    }
}
