package com.vikche.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Forecast extends AppCompatActivity implements Constants {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "First launch";
        } else {
            instanceState = "Repeated launch";
        }
        if (BuildConfig.USE_LOG) {
            Log.d(TAG, instanceState + " - onCreate()");
        }
        String intentText = getIntent().getExtras().getString(TEXT);
        TextView cityOutput = findViewById(R.id.city_output_tv);
        cityOutput.setText(intentText);

        Button backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button aboutCityBtn = findViewById(R.id.about_city_btn);
        aboutCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getString(R.string.wiki) + cityOutput.getText().toString();
                Uri uri = Uri.parse(url);
                Intent openBrowser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(openBrowser);
            }
        });
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