package com.tapptitude.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexpuhalschi on 27/06/2017.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {
    private List<WeatherListItem> weatherListItemList = new ArrayList<>();

    public WeatherListAdapter(List<WeatherListItem> weatherListItemList) {
        this.weatherListItemList = weatherListItemList;
    }

    @Override
    public WeatherListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.ViewHolder holder, int position) {
        holder.titleTV.setText(weatherListItemList.get(position).getTitle());
        holder.numberTV.setText(String.valueOf(weatherListItemList.get(position).getNumber()));
    }

    @Override
    public int getItemCount() {
        return weatherListItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV;
        TextView numberTV;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titleTV = itemView.findViewById(R.id.il_tv_title);
            this.numberTV = itemView.findViewById(R.id.il_tv_number);
        }
    }
}
