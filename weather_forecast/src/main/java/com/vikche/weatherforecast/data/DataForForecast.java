package com.vikche.weatherforecast.data;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class DataForForecast implements Parcelable {
    private String cityName = "";
    private boolean isWindBoxChecked;
    private boolean isPressureBoxChecked;
    private int resourceIndex;

    public String getCityName() {
        return cityName;
    }

    public boolean isWindBoxChecked() {
        return isWindBoxChecked;
    }

    public boolean isPressureBoxChecked() {
        return isPressureBoxChecked;
    }

    public int getResourceIndex() {
        return resourceIndex;
    }

    public DataForForecast(String cityName, boolean isWindBoxChecked, boolean isPressureBoxChecked, int resourceIndex) {
        this.cityName = cityName;
        this.isWindBoxChecked = isWindBoxChecked;
        this.isPressureBoxChecked = isPressureBoxChecked;
        this.resourceIndex = resourceIndex;
    }

    public DataForForecast() {
    }

    public static final Creator<DataForForecast> CREATOR = new Creator<DataForForecast>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public DataForForecast createFromParcel(Parcel source) {
            String cityName = source.readString();
            boolean isWindBoxChecked = source.readBoolean();
            boolean isPressureBoxChecked = source.readBoolean();
            int resourceIndex = source.readInt();
            return new DataForForecast(cityName, isWindBoxChecked, isPressureBoxChecked, resourceIndex);
        }

        @Override
        public DataForForecast[] newArray(int size) {
            return new DataForForecast[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityName);
        dest.writeBoolean(isWindBoxChecked);
        dest.writeBoolean(isPressureBoxChecked);
        dest.writeInt(resourceIndex);
    }
}
