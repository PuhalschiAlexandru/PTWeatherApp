package com.tapptitude.weatherapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static Utils.ColorUtils.blendColors;

/**
 * Created by alexpuhalschi on 27/06/2017.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {
    private final int mTitleEndColor;
    private final int mNumberStartColor;
    private final int mNumberEndColor;
    private final int mTitleStartColor;

    private List<WeatherListItem> mWeatherListItemList;

    private Context mContext;

    public WeatherListAdapter(Context context, List<WeatherListItem> mWeatherListItemList) {
        this.mWeatherListItemList = mWeatherListItemList;
        mContext = context;

        mTitleStartColor = ContextCompat.getColor(context, R.color.initial_text_gradient_color);
        mTitleEndColor = ContextCompat.getColor(context, R.color.final_text_gradient_color);
        mNumberStartColor = ContextCompat.getColor(context, R.color.initial_number_gradient_color);
        mNumberEndColor = ContextCompat.getColor(context, R.color.final_number_gradient_color);
    }

    @Override
    public WeatherListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.ViewHolder holder, int position) {
        holder.mTitleTV.setBackgroundColor(blendColors(mTitleStartColor, mTitleEndColor, getBlendRatioForPosition(position)));
        holder.mNumberTV.setBackgroundColor(blendColors(mNumberStartColor, mNumberEndColor, getBlendRatioForPosition(position)));
        holder.mTitleTV.setText(mWeatherListItemList.get(position).getTitle());
        holder.mNumberTV.setText(String.valueOf(mWeatherListItemList.get(position).getNumber()));
    }

    @Override
    public int getItemCount() {
        return mWeatherListItemList == null ? 0 : mWeatherListItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.il_tv_title)
        TextView mTitleTV;
        @BindView(R.id.il_tv_number)
        TextView mNumberTV;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private float getBlendRatioForPosition(int position) {
        if (position == 0) {
            return 1;
        } else if (position == getItemCount() - 1) {
            return 0;
        } else {
            float step = (float) 1 / getItemCount();
            return 1 - (position + 1) * step;
        }
    }
}
