package com.vikche.weatherforecast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import static com.vikche.weatherforecast.ForecastFragment.PARCEL;

public class ParametersFragment extends Fragment implements Constants {
    private AutoCompleteTextView cityActv;
    private DataForForecast dataForForecast;
    private boolean isLandscape;
    private CheckBox cbWindSpeed;
    private CheckBox cbAtmPressure;

    //fragment view creation, layout inflation
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parameters, container, false);
    }

    //fragment view created, layout creation
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DrawerLayout drawerLayout = view.findViewById(R.id.drawer_lo);
        view.findViewById(R.id.menu_btn_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //implementation of autoCompleteTextView functionality
        String[] cities = getResources().getStringArray(R.array.cities);
        cityActv = view.findViewById(R.id.city_actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, cities);
        cityActv.setAdapter(adapter);

        cbWindSpeed = view.findViewById(R.id.wind_speed_cb);
        cbAtmPressure = view.findViewById(R.id.atm_pressure_cb);
        cbWindSpeed.setChecked(Singleton.getInstance().isWindBoxChecked());
        cbAtmPressure.setChecked(Singleton.getInstance().isPressureBoxChecked());

        Button checkBtn = view.findViewById(R.id.check_btn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityMatch = null;
                int elemIndex = 0;
                for (int i = 0; i < cities.length; i++) {

                    if (cityActv.getText().toString().equals(cities[i])) {
                        cityMatch = cities[i];
                        elemIndex = i;
                        break;
                    }
                }
                if (!cityActv.getText().toString().equals(cityMatch)) {
                    Toast.makeText(v.getContext(), "Please, enter correct city",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                memorizeData();
                dataForForecast = new DataForForecast(cityActv.getText().toString(), cbWindSpeed.isChecked(), cbAtmPressure.isChecked(), elemIndex);
                showForecast(dataForForecast);
            }
        });
    }

    //activity created, check if orientation - landscape
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            dataForForecast = savedInstanceState.getParcelable("ChosenCity");
        } else {
            dataForForecast = new DataForForecast(getResources().getStringArray(R.array.cities)[0], cbWindSpeed.isChecked(), cbAtmPressure.isChecked(), 0);
        }
        if (isLandscape) {
            showForecast(dataForForecast);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("ChosenCity", dataForForecast);
        super.onSaveInstanceState(outState);
    }

    public void memorizeData() {
        Singleton.getInstance().setWindBoxChecked(cbWindSpeed.isChecked());
        Singleton.getInstance().setPressureBoxChecked(cbAtmPressure.isChecked());
    }

    public void showForecast(DataForForecast dataForForecast) {
        if (isLandscape) {
            ForecastFragment forecast = (ForecastFragment) getFragmentManager().findFragmentById(R.id.fragment_forecast);

            if (forecast == null || forecast.getParcel().getCityName() != dataForForecast.getCityName()) {
                forecast = ForecastFragment.create(dataForForecast);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_forecast, forecast);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), ForecastActivity.class);
            intent.putExtra(PARCEL, dataForForecast);
            startActivity(intent);
        }
    }
}