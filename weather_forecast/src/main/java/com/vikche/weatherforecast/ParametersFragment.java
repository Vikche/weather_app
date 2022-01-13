package com.vikche.weatherforecast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import static com.vikche.weatherforecast.ForecastFragment.PARCEL;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ParametersFragment extends Fragment implements Constants {
    private DataForForecast dataForForecast;
    private boolean isLandscape;

    //fragment view creation, layout inflation
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parameters, container, false);
    }

    //fragment view created, layout creation
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        implementToolbar();

        implementInterface();
    }

    private void implementToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbar_tb);
        DrawerLayout drawerLayout = getView().findViewById(R.id.drawer_lo);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.developers:
                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
                        bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "Info");
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

        private void implementInterface () {
            //implementation of autoCompleteTextView functionality
            String[] cities = getResources().getStringArray(R.array.cities);
            AutoCompleteTextView actvCity = getView().findViewById(R.id.city_actv);
            ArrayAdapter<String> actvAdapter = new ArrayAdapter<>(getView().getContext(),
                    android.R.layout.simple_list_item_1, cities);
            actvCity.setAdapter(actvAdapter);

            //CheckBoxes initialization
            CheckBox cbWindSpeed = getView().findViewById(R.id.wind_speed_cb);
            CheckBox cbAtmPressure = getView().findViewById(R.id.atm_pressure_cb);
            cbWindSpeed.setChecked(Singleton.getInstance().isWindBoxChecked());
            cbAtmPressure.setChecked(Singleton.getInstance().isPressureBoxChecked());

            //implementation of ListView
            ListView lvCitiesHistory = getView().findViewById(R.id.cities_history_lv);
            ArrayList<String> alCitiesHistory = Singleton.getInstance().getCities();
            ArrayAdapter<String> aaCitiesHistory = new ArrayAdapter<>(getActivity(), R.layout.city_item,
                    alCitiesHistory);
            lvCitiesHistory.setAdapter(aaCitiesHistory);
            lvCitiesHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView = (TextView) view;
                    int elemIndex = 0;
                    for (int i = 0; i < cities.length; i++) {
                        if (textView.getText().toString().equals(cities[i])) {
                            elemIndex = i;
                        }
                    }
                    dataForForecast = new DataForForecast(textView.getText().toString(),
                            cbWindSpeed.isChecked(), cbAtmPressure.isChecked(), elemIndex);
                    Snackbar.make(view, "Confirm check action", Snackbar.LENGTH_LONG).
                            setAction("Confirm", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showForecast(dataForForecast);
                                }
                            }).show();
                }
            });

            Button checkBtn = getView().findViewById(R.id.check_btn);
            checkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String cityMatch = null;
                    int elemIndex = 0;
                    for (int i = 0; i < cities.length; i++) {

                        if (actvCity.getText().toString().equals(cities[i])) {
                            cityMatch = cities[i];
                            elemIndex = i;
                            break;
                        }
                    }
                    if (!actvCity.getText().toString().equals(cityMatch)) {
                        Snackbar.make(v, "Please, enter correct city", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    memorizeData(cbWindSpeed, cbAtmPressure, actvCity);
                    aaCitiesHistory.notifyDataSetChanged();
                    dataForForecast = new DataForForecast(actvCity.getText().toString(),
                            cbWindSpeed.isChecked(), cbAtmPressure.isChecked(), elemIndex);
                    actvCity.setText("");
                    Snackbar.make(getView(), "Confirm check action", Snackbar.LENGTH_LONG).
                            setAction("Confirm", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showForecast(dataForForecast);
                                }
                            }).show();
                }
            });
        }

        //activity created, check if orientation - landscape
        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
            isLandscape = getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_LANDSCAPE;
            if (savedInstanceState != null) {
                dataForForecast = savedInstanceState.getParcelable("ChosenCity");
            } else {
                dataForForecast = new DataForForecast();
            }
            if (isLandscape) {
                showForecast(dataForForecast);
            }
        }

        @Override
        public void onSaveInstanceState (@NonNull Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putParcelable("ChosenCity", dataForForecast);
        }

        public void memorizeData (CheckBox cbWindSpeed, CheckBox cbAtmPressure,
                AutoCompleteTextView actv){
            Singleton.getInstance().setWindBoxChecked(cbWindSpeed.isChecked());
            Singleton.getInstance().setPressureBoxChecked(cbAtmPressure.isChecked());
            Singleton.getInstance().addCities(actv.getText().toString());
        }

        public void showForecast (DataForForecast dataForForecast){
            if (isLandscape) {
                ForecastFragment forecast = (ForecastFragment) getFragmentManager().
                        findFragmentById(R.id.forecast_fragment_container_land);

                if (forecast == null || forecast.getParcel().getCityName() !=
                        dataForForecast.getCityName()) {

                    forecast = ForecastFragment.create(dataForForecast);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.forecast_fragment_container_land, forecast);
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