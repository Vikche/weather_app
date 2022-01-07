package com.vikche.weatherforecast.forecastlist;

import android.content.res.Resources;

import com.vikche.weatherforecast.R;

import java.util.ArrayList;
import java.util.List;

public class CardDS implements CardDSInterface {
    private List<Card> dataSource;
    private Resources resources;

    public CardDS(Resources resources) {
        dataSource = new ArrayList<>(7);
        this.resources = resources;
    }

    public CardDS init() {
        String[] days = resources.getStringArray(R.array.week_days);

        for (int i = 0; i < days.length; i++) {
            dataSource.add(new Card(days[i]));
        }
        return this;
    }

    @Override
    public Card getCard(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }
}
