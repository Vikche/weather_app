package com.vikche.weatherforecast.ui.home;

import android.content.Context;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vikche.weatherforecast.CityActivity;
import com.vikche.weatherforecast.R;
import com.vikche.weatherforecast.data.DataForForecast;
import com.vikche.weatherforecast.forecastlist.CardAdapter;
import com.vikche.weatherforecast.forecastlist.CardDS;
import com.vikche.weatherforecast.forecastlist.CardDSInterface;

public class ForecastFragment extends Fragment {
    public static final String PARCEL = "Data";

    private CityViewModel cityViewModel;
    private DataForForecast dataForForecast;

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
        return inflater.inflate(R.layout.fragment_forecast,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            dataForForecast = getParcel();

        if (!dataForForecast.getCityName().equals("")) {
            TextView cityName = view.findViewById(R.id.city_tv);
            ImageView coaImg = view.findViewById(R.id.coat_of_arms_iv);
            TextView tempVal = view.findViewById(R.id.temperature_tv);
            TextView tempMark = view.findViewById(R.id.c);
            RecyclerView recyclerView = view.findViewById(R.id.week_forecast_rv);
            TypedArray coaImgs = getResources().obtainTypedArray(R.array.coa_imgs);
            String[] tempValues = getResources().getStringArray(R.array.temperatures);

            int resInd = dataForForecast.getResourceIndex();

            cityName.setText(dataForForecast.getCityName());
            coaImg.setImageResource(coaImgs.getResourceId(resInd, -1));
            tempVal.setText(tempValues[resInd]);
            tempVal.setVisibility(View.VISIBLE);
            tempMark.setVisibility(View.VISIBLE);
            initDataSource(recyclerView);

            if (dataForForecast.isWindBoxChecked()) {
                TextView windSpeed = view.findViewById(R.id.wind_tv);
                String[] windSpeeds = getResources().getStringArray(R.array.wind_speeds);
                windSpeed.setText(windSpeeds[resInd]);
                TextView windMark = view.findViewById(R.id.m_s);
                windMark.setVisibility(View.VISIBLE);
            }
            if (dataForForecast.isPressureBoxChecked()) {
                TextView atmPressure = view.findViewById(R.id.pressure_tv);
                String[] atmPressures = getResources().getStringArray(R.array.atm_pressures);
                atmPressure.setText(atmPressures[resInd]);
                TextView pressMark = view.findViewById(R.id.mm_hg);
                pressMark.setVisibility(View.VISIBLE);
            }
            Button aboutCityBtn = view.findViewById(R.id.about_city_btn);
            aboutCityBtn.setVisibility(View.VISIBLE);
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

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Button backBtn = view.findViewById(R.id.back_btn);
            backBtn.setVisibility(View.VISIBLE);
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }
    }

    private void initDataSource(RecyclerView recyclerView) {
        CardDSInterface dataSource = new CardDS(getResources()).init();
        final CardAdapter adapter = initRecyclerView(dataSource, recyclerView);
    }

    private CardAdapter initRecyclerView(CardDSInterface dataSource, RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(dataSource);
        recyclerView.setAdapter(cardAdapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getActivity().getDrawable(R.drawable.card_separator));
        recyclerView.addItemDecoration(itemDecoration);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(500);
        animator.setRemoveDuration(500);
        recyclerView.setItemAnimator(animator);

        cardAdapter.SetOnItemClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), String.format("Position - %d", position)
                        , Toast.LENGTH_SHORT).show();
            }
        });
        return cardAdapter;
    }
}
