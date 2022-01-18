package com.vikche.weatherforecast.ui.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.vikche.weatherforecast.R;

public class SettingsActivity extends Fragment {
    private static final int SETTING_CODE = 88;

    public SettingsActivity() {
        // Required empty public constructor
    }

    public static SettingsActivity newInstance() {
        SettingsActivity fragment = new SettingsActivity();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        SwitchMaterial switchMode = view.findViewById(R.id.switch_mode_fragment);
//        switchMode.setChecked(isDarkTheme());
//        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                setDarkTheme(isChecked);
//                getActivity().recreate();
//            }
//        });
    }
}