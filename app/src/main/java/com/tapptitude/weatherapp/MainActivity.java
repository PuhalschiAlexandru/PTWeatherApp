package com.tapptitude.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.am_rv_main_view)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private boolean mReverseState = true;
    private boolean mSortState = false;
    private List<WeatherListItem> mWeatherListItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mWeatherListItemList = new ArrayList<>();

        initRV();
    }

    private void initRV() {
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new WeatherListAdapter(getTestData());
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<WeatherListItem> getTestData() {

        mWeatherListItemList.add(WeatherListItemFactory.getWeatherListItem());
        mWeatherListItemList.add(WeatherListItemFactory.getWeatherListItem());
        mWeatherListItemList.add(WeatherListItemFactory.getWeatherListItem());
        mWeatherListItemList.add(WeatherListItemFactory.getWeatherListItem());

        return mWeatherListItemList;
    }

    @OnClick(R.id.am_bt_delete)
    public void deleteButtonClicked() {
        mWeatherListItemList.remove(mWeatherListItemList.size() - 1);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.am_bt_add)
    public void addButtonclicked() {
        mWeatherListItemList.add(0, WeatherListItemFactory.getWeatherListItem());
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.am_bt_reverse)
    public void reverseButtonClicked(){
        mLayoutManager.setReverseLayout(mReverseState);
        mReverseState = !mReverseState;
    }
    @OnClick(R.id.am_bt_sort)
    public void sortButtonClicked(){
        if (!mSortState) {
            compareListByTitle(mWeatherListItemList);
            mAdapter.notifyDataSetChanged();
            mSortState = true;
        } else {
            compareListByNumber(mWeatherListItemList);
            mAdapter.notifyDataSetChanged();
            mSortState = false;
        }
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
