package com.vikche.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseCity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);

        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "First launch";
        } else {
            instanceState = "Repeated launch";
        }
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, instanceState + " - onCreate()");
        }
//implementation of autoCompleteTextView functionality
        String[] cities = getResources().getStringArray(R.array.cities);
        AutoCompleteTextView actv = findViewById(R.id.auto_city_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,cities);
        actv.setAdapter(adapter);
        CheckBox windSpeed = findViewById(R.id.wind_speed_box);
        CheckBox atmPressure = findViewById(R.id.atm_pressure_box);

        windSpeed.setChecked(MainPresenter.getInstance().isWindBoxChecked());
        atmPressure.setChecked(MainPresenter.getInstance().isPressureBoxChecked());

        Button checkBtn = findViewById(R.id.check_btn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainPresenter.getInstance().setWindBoxChecked(windSpeed.isChecked());
                MainPresenter.getInstance().setPressureBoxChecked(atmPressure.isChecked());
                showWeather(actv);
            }
        });
    }

    public void showWeather(AutoCompleteTextView actv) {
        MainPresenter.getInstance().setCityNameInput(actv.getText().toString());
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, "onStart()");
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, "Repeated launch - onRestoreInstanceState()");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, "onResume()");
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
    }
}