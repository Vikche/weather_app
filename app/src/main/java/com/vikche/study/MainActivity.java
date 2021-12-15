package com.vikche.study;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "First launch";
        } else {
            instanceState = "Repeated launch";
        }
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, instanceState + " - onCreate()");
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        findViewById(R.id.menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        Button chooseCityButton = findViewById(R.id.choose_btn);
        chooseCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChooseCity();
            }
        });

        TextView cityNameOutput = findViewById(R.id.city_name_output);
        MainPresenter presenter = MainPresenter.getInstance();

        cityNameOutput.setText(presenter.getCityNameInput());
    }

    public void openChooseCity() {
        Intent intent = new Intent(this,ChooseCity.class);
        startActivity(intent);
    }

    @Override
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
    }
}