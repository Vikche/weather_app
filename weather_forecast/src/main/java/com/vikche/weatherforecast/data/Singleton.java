package com.vikche.weatherforecast.data;

import java.util.ArrayList;

//Singleton
public final class Singleton {
    private static Singleton instance = null;
    private static final Object SYNC_OBJ = new Object();
    private ArrayList<String> cities = new ArrayList<>();

    private Singleton() {
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void addCities(String city) {
        if (cities.contains(city)) {
            cities.remove(city);
            cities.add(0,city);
        } else {
            cities.add(0,city);
        }
    }

    public static Singleton getInstance() {
        synchronized (SYNC_OBJ) {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
}
