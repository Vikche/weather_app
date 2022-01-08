package com.vikche.weatherforecast;

import java.util.ArrayList;
import java.util.List;

//Singleton
public final class Singleton {
    private static Singleton instance = null;
    private static final Object SYNC_OBJ = new Object();
    private boolean isWindBoxChecked;
    private boolean isPressureBoxChecked;
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

    public boolean isWindBoxChecked() {
        return isWindBoxChecked;
    }

    public void setWindBoxChecked(boolean windBoxChecked) {
        isWindBoxChecked = windBoxChecked;
    }

    public boolean isPressureBoxChecked() {
        return isPressureBoxChecked;
    }

    public void setPressureBoxChecked(boolean pressureBoxChecked) {
        isPressureBoxChecked = pressureBoxChecked;
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
