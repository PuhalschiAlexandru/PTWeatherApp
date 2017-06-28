package com.tapptitude.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    boolean mReverseState = true;
    boolean mSortState = false;

    List<WeatherListItem> weatherListItemList = new ArrayList<>();

    Button mDeleteButton;
    Button mAddButton;
    Button mReverseButton;
    Button mSortButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRV();
        initializeButton();
    }

    private void initRV() {
        mRecyclerView = (RecyclerView) findViewById(R.id.am_rv_main_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

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
        mAddButton = (Button) findViewById(R.id.am_bt_add);
        mReverseButton = (Button) findViewById(R.id.am_bt_reverse);
        mSortButton = (Button) findViewById(R.id.am_bt_sort);

        View.OnClickListener deleteButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherListItemList.remove(weatherListItemList.size() - 1);
                mAdapter.notifyDataSetChanged();
            }
        };

        View.OnClickListener addButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherListItemList.add(0, WeatherListItemFactory.getWeatherListItem());
                mAdapter.notifyDataSetChanged();
            }
        };
        View.OnClickListener reverseButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayoutManager.setReverseLayout(mReverseState);
                if (mReverseState) {
                    mReverseState = false;
                } else {
                    mReverseState = true;
                }
            }
        };
        View.OnClickListener sortButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mSortState) {
                    compareListByTitle(weatherListItemList);
                    mAdapter.notifyDataSetChanged();
                    mSortState = true;
                } else {
                    compareListByNumber(weatherListItemList);
                    mAdapter.notifyDataSetChanged();
                    mSortState = false;
                }
            }
        };

        mDeleteButton.setOnClickListener(deleteButtonClickListener);
        mAddButton.setOnClickListener(addButtonClickListener);
        mReverseButton.setOnClickListener(reverseButtonOnClickListener);
        mSortButton.setOnClickListener(sortButtonOnClickListener);
    }

    private void compareListByTitle(List<WeatherListItem> listToSort) {
        Collections.sort(listToSort, new Comparator<WeatherListItem>() {
            @Override
            public int compare(WeatherListItem weatherListItem, WeatherListItem t1) {
                return weatherListItem.getTitle().compareToIgnoreCase(t1.getTitle());
            }
        });
    }

    private void compareListByNumber(List<WeatherListItem> listToSort) {
        Collections.sort(listToSort, new Comparator<WeatherListItem>() {
            @Override
            public int compare(WeatherListItem weatherListItem, WeatherListItem t1) {
                return weatherListItem.getNumber() - (t1.getNumber());
            }
        });
    }
}
