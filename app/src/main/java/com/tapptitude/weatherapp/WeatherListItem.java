package com.tapptitude.weatherapp;


/**
 * Created by alexpuhalschi on 27/06/2017.
 */

public class WeatherListItem {
    private String title;
    private int number;

    public WeatherListItem(String title, int number) {
        this.title = title;
        this.number = number;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setNumber(int newNumber) {
        number = newNumber;
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }
    
}
