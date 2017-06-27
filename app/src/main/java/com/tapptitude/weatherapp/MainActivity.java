package com.tapptitude.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    List<WeatherListItem> weatherListItemList = new ArrayList<>();

    Button mDeleteButton;
    Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRV();
        initializeButton();
    }

    private void initRV() {
        mRecyclerView = (RecyclerView) findViewById(R.id.am_rv_main_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new WeatherListAdapter(getTestData());
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<WeatherListItem> getTestData() {

        weatherListItemList.add(WeatherListItemFactory.getWeatherListItem());
        weatherListItemList.add(WeatherListItemFactory.getWeatherListItem());
        weatherListItemList.add(WeatherListItemFactory.getWeatherListItem());
        weatherListItemList.add(WeatherListItemFactory.getWeatherListItem());

        return weatherListItemList;
    }

    public void initializeButton() {
        mDeleteButton = (Button) findViewById(R.id.am_bt_delete);
        mAddButton = (Button) findViewById(R.id.al_bt_add);

        View.OnClickListener deleteButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherListItemList.remove(weatherListItemList.size()-1);
                mAdapter.notifyItemRemoved(weatherListItemList.size());

            }
        };

        View.OnClickListener addButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherListItemList.add(0,WeatherListItemFactory.getWeatherListItem());
                mAdapter.notifyDataSetChanged();
            }
        };

        mDeleteButton.setOnClickListener(deleteButtonClickListener);
        mAddButton.setOnClickListener(addButtonClickListener);
    }

}
