package com.tapptitude.weatherapp;

import java.util.Random;

/**
 * Created by alexpuhalschi on 27/06/2017.
 */

public class WeatherListItemFactory {
    public static WeatherListItem getWeatherListItem() {
        return new WeatherListItem(generateString(), generateInt());
    }

    private static String generateString() {
        Random randomLength = new Random();
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < randomLength.nextInt(6) + 2; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

    private static int generateInt() {
        Random ran = new Random();
        return ran.nextInt(10) + 1;
    }
}
