package com.vikche.study;

import android.os.Parcelable;

public class Parcel implements Parcelable {
    private String city;
    private Integer airTemp;
    private Integer windSpeed;
    private Integer atmPressure;

    protected Parcel(android.os.Parcel in) {
        city = in.readString();
        if (in.readByte() == 0) {
            airTemp = null;
        } else {
            airTemp = in.readInt();
        }
        if (in.readByte() == 0) {
            windSpeed = null;
        } else {
            windSpeed = in.readInt();
        }
        if (in.readByte() == 0) {
            atmPressure = null;
        } else {
            atmPressure = in.readInt();
        }
    }

    public static final Creator<Parcel> CREATOR = new Creator<Parcel>() {
        @Override
        public Parcel createFromParcel(android.os.Parcel in) {
            return new Parcel(in);
        }

        @Override
        public Parcel[] newArray(int size) {
            return new Parcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(city);
        if (airTemp == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(airTemp);
        }
        if (windSpeed == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(windSpeed);
        }
        if (atmPressure == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(atmPressure);
        }
    }
}
