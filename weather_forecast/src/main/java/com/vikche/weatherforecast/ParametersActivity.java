package com.vikche.weatherforecast;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ParametersActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   /* @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.USE_LOG) {
            Log.d(TAG,"onStart()");
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (BuildConfig.USE_LOG) {
            Log.d(TAG,"Repeated launch - onRestoreInstanceState()");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.USE_LOG) {
            Log.d(TAG,"onResume()");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, "onPause()");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, "onSaveInstanceState()");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, "onStop()");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, "onRestart()");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, "onDestroy()");
        }
    }*/
}