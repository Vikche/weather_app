package com.vikche.weatherforecast.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CityViewModel extends ViewModel {

    private MutableLiveData<Boolean> cbParams;

    public CityViewModel() {
        cbParams = new MutableLiveData<>();
    }

    public LiveData<Boolean> getCheckedBoxes() {
        return cbParams;
    }
}
