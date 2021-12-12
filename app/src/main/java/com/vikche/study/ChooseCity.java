package com.vikche.study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class ChooseCity extends AppCompatActivity {
        private AutoCompleteTextView actv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);

        String[] cities = getResources().getStringArray(R.array.cities);
        actv = findViewById(R.id.auto_city_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,cities);
        actv.setAdapter(adapter);

        Button checkBtn = findViewById(R.id.check_btn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeather();
            }
        });
    }

    public void showWeather() {
        Intent intent = new Intent(this,MainActivity.class);
        TextView showCityText = findViewById(R.id.city_name);
        showCityText.setText(actv.getText());
        startActivity(intent);
    }
}