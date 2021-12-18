package com.vikche.study;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Parameters extends AppCompatActivity implements Constants {
    private final String TAG = this.getClass().getSimpleName();
    AutoCompleteTextView cityActv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "First launch";
        } else {
            instanceState = "Repeated launch";
        }
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, instanceState + " - onCreate()");
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_lo);
        findViewById(R.id.menu_btn_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //implementation of autoCompleteTextView functionality
        String[] cities = getResources().getStringArray(R.array.cities);
        cityActv = findViewById(R.id.city_actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,cities);
        cityActv.setAdapter(adapter);

        CheckBox cbWindSpeed = findViewById(R.id.wind_speed_cb);
        CheckBox cbAtmPressure = findViewById(R.id.atm_pressure_cb);
        cbWindSpeed.setChecked(MainPresenter.getInstance().isWindBoxChecked());
        cbAtmPressure.setChecked(MainPresenter.getInstance().isPressureBoxChecked());

        Button checkBtn = findViewById(R.id.check_btn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityMatch = null;
                for (String city : cities) {
                    if (cityActv.getText().toString().equals(city)) {
                        cityMatch = city;
                        break;
                    }
                }
                if (!cityActv.getText().toString().equals(cityMatch)) {
                    Toast.makeText(getApplicationContext(), "Please, enter correct city",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                memorizeData(cbWindSpeed, cbAtmPressure);
                showBroadcast();
            }
        });
    }

    public void memorizeData(CheckBox cbWindSpeed, CheckBox cbAtmPressure) {
        MainPresenter.getInstance().setWindBoxChecked(cbWindSpeed.isChecked());
        MainPresenter.getInstance().setPressureBoxChecked(cbAtmPressure.isChecked());
    }

    public void showBroadcast() {
        Intent intent = new Intent(Parameters.this, Forecast.class);
        intent.putExtra(TEXT, cityActv.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.USE_LOG) {
            Log.d(TAG,"onStart()");
        }
        cityActv.setText("");
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
    }
}