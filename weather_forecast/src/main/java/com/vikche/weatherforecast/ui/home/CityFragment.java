package com.vikche.weatherforecast.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.vikche.weatherforecast.databinding.FragmentCityBinding;
import com.vikche.weatherforecast.R;
import com.vikche.weatherforecast.data.DataForForecast;
import com.vikche.weatherforecast.data.Singleton;

import java.util.ArrayList;

public class CityFragment extends Fragment {
    private DataForForecast dataForForecast;
    private CityViewModel cityViewModel;
    private FragmentCityBinding binding;
    private OnCityFragmentListener callBack;

    public interface OnCityFragmentListener {
        void showForecast(DataForForecast dataForForecast);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCityFragmentListener) {
            callBack = (OnCityFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement Interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBack = null;
    }

    //fragment view creation, layout inflation
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);
        binding = FragmentCityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    //fragment view created, layout creation
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        implementLayout();
    }

    private void implementLayout() {
        //implementation of autoCompleteTextView functionality
        String[] cities = getResources().getStringArray(R.array.cities);
        MaterialAutoCompleteTextView actvCity = getView().findViewById(R.id.city_actv);
        ArrayAdapter<String> actvAdapter = new ArrayAdapter<>(getView().getContext(),
                android.R.layout.simple_list_item_1, cities);
        actvCity.setAdapter(actvAdapter);
        //CheckBoxes, ListView initialization
        final MaterialCheckBox windSpeed = binding.windSpeedCb;
        final MaterialCheckBox atmPressure = binding.atmPressureCb;
        cityViewModel.getCheckedBoxes().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                windSpeed.setChecked(aBoolean);
                atmPressure.setChecked(aBoolean);
            }
        });

        final ListView citiesHistoryLV = binding.citiesHistoryLv;
        ArrayList<String> citiesHistoryAL = Singleton.getInstance().getCities();
        ArrayAdapter<String> citiesHistoryAA = new ArrayAdapter<>(getActivity(), R.layout.item_city,
                citiesHistoryAL);
        citiesHistoryLV.setAdapter(citiesHistoryAA);
        citiesHistoryLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                        windSpeed.isChecked(), atmPressure.isChecked(), elemIndex);
                callBack.showForecast(dataForForecast);
//                Snackbar.make(view, "Confirm check action", Snackbar.LENGTH_LONG).
//                        setAction("Confirm", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                callBack.showForecast(dataForForecast);
//                            }
//                        }).show();
            }
        });

        MaterialButton checkBtn = getView().findViewById(R.id.check_btn);
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
                memorizeData(actvCity);
                citiesHistoryAA.notifyDataSetChanged();
                dataForForecast = new DataForForecast(actvCity.getText().toString(),
                        windSpeed.isChecked(), atmPressure.isChecked(), elemIndex);
                actvCity.setText("");
                callBack.showForecast(dataForForecast);

//                Snackbar.make(getView(), "Confirm check action", Snackbar.LENGTH_LONG).
//                        setAction("Confirm", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                                callBack.showForecast(dataForForecast);
//                            }
//                        }).show();
            }
        });
    }

    public void memorizeData(AutoCompleteTextView actv) {
        Singleton.getInstance().addCities(actv.getText().toString());
    }
}