package com.vikche.weatherforecast;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ForecastFragment extends Fragment {
    public static final String PARCEL = "parcel";

    //fabric method for fragment creation
    public static ForecastFragment create(DataForForecast dataForForecast) {
        ForecastFragment forecastFragment = new ForecastFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(PARCEL, dataForForecast);
        forecastFragment.setArguments(bundle);
        return forecastFragment;
    }

    public DataForForecast getParcel() {
        return getArguments().getParcelable(PARCEL);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView cityName = view.findViewById(R.id.city_tv);
        ImageView coaImg = view.findViewById(R.id.coat_of_arms);
        TextView tempVal = view.findViewById(R.id.temperature_tv);
        TextView windMark = view.findViewById(R.id.m_s);
        TextView pressMark = view.findViewById(R.id.mm_hg);

        TypedArray coaImgs = getResources().obtainTypedArray(R.array.coa_imgs);
        String[] tempValues = getResources().getStringArray(R.array.temperatures);

        DataForForecast dataForForecast = getParcel();
        int resInd = dataForForecast.getResourceIndex();

        cityName.setText(dataForForecast.getCityName());
        coaImg.setImageResource(coaImgs.getResourceId(resInd,-1));
        tempVal.setText(tempValues[resInd]);

        if (dataForForecast.isWindBoxChecked()) {
            TextView windSpeed = view.findViewById(R.id.wind_tv);
            String[] windSpeeds = getResources().getStringArray(R.array.wind_speeds);
            windSpeed.setText(windSpeeds[resInd]);
            windMark.setVisibility(View.VISIBLE);
        }
        if (dataForForecast.isPressureBoxChecked()) {
            TextView atmPressure = view.findViewById(R.id.pressure_tv);
            String[] atmPressures = getResources().getStringArray(R.array.atm_pressures);
            atmPressure.setText(atmPressures[resInd]);
            pressMark.setVisibility(View.VISIBLE);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Button backBtn = view.findViewById(R.id.back_btn);
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }

        Button aboutCityBtn = view.findViewById(R.id.about_city_btn);
        aboutCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getString(R.string.wiki) + cityName.getText().toString();
                Uri uri = Uri.parse(url);
                Intent openBrowser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(openBrowser);
            }
        });
    }
}
