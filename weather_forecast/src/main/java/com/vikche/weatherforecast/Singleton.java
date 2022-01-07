package com.vikche.weatherforecast;

import java.util.List;

//Singleton
public final class Singleton {
    private static Singleton instance = null;
    private static final Object SYNC_OBJ = new Object();
    private boolean isWindBoxChecked;
    private boolean isPressureBoxChecked;
    private List<String> cities;

    private Singleton() {
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
