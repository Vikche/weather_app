package com.vikche.weatherforecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

public abstract class SettingBaseActivity extends AppCompatActivity {
    private static final String NAME_SHARED_PREFERENCE = "LOGIN";
    private static final String IS_DARK_THEME = "IS_DARK_THEME";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isDarkTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    protected boolean isDarkTheme() {
        SharedPreferences sharedPrefs = getSharedPreferences(NAME_SHARED_PREFERENCE,
                Context.MODE_PRIVATE);
        return sharedPrefs.getBoolean(IS_DARK_THEME,true);
    }

    protected void setDarkTheme(boolean isDarkTheme) {
        SharedPreferences sharedPrefs = getSharedPreferences(NAME_SHARED_PREFERENCE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(IS_DARK_THEME, isDarkTheme);
        editor.apply();
    }
}
