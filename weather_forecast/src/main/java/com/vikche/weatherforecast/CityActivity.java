package com.vikche.weatherforecast;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.vikche.weatherforecast.data.DataForForecast;
import com.vikche.weatherforecast.databinding.ActivityCityBinding;
import com.vikche.weatherforecast.ui.home.CityFragment;
import com.vikche.weatherforecast.ui.home.ForecastFragment;

public class CityActivity extends SettingBaseActivity implements CityFragment.OnCityFragmentListener {
    CityFragment cityFragment;
    ForecastFragment forecastFragment;
    FragmentManager fragmentManager;
    NavController navController;

    private ActivityCityBinding binding;
    private DataForForecast dataForForecast;
    private boolean isLandscape;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;

        navController = Navigation.findNavController(this,
                R.id.fragment_nav_host);
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
//        NavigationUI.setupActionBarWithNavController(this,navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
//                switch (destination) {
//                    case R.
//                }
            }
        });

        implementToolbar();

        if (savedInstanceState != null) {
            dataForForecast = savedInstanceState.getParcelable("Data");
        } else {
            dataForForecast = new DataForForecast();
        }
        if (isLandscape) {
            showForecast(dataForForecast);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("Data",dataForForecast);
    }

    private void implementToolbar() {
        Toolbar toolbar = binding.topToolbarLo;
        DrawerLayout drawer = binding.drawerLo;
        toolbar.setNavigationOnClickListener(v -> drawer.openDrawer(GravityCompat.START));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.developers:
                        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                        bottomSheetFragment.show(getSupportFragmentManager().beginTransaction(), "Info");
                        return true;
                    default:
                        return false;
                }
            }
        });
        //Implement LightDark setting
        NavigationView navigationView = binding.menuNv;
        MenuItem modeItem = navigationView.getMenu().findItem(R.id.switch_mode);
        SwitchMaterial switchMode = modeItem.getActionView().findViewById(R.id.drawer_switch);
        switchMode.setChecked(isDarkTheme());
        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setDarkTheme(isChecked);
                recreate();
            }
        });
    }

    @Override
    public void showForecast(DataForForecast dataForForecast) {
        fragmentManager = getSupportFragmentManager();
        forecastFragment = (ForecastFragment) fragmentManager.findFragmentById(R.id.forecast_container);
        if (forecastFragment == null || forecastFragment.getParcel().getCityName() !=
                dataForForecast.getCityName()) {
            if (isLandscape) {
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.forecast_container, ForecastFragment.create(dataForForecast))
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            } else {
                Bundle bundle = new Bundle();
                bundle.putParcelable("Data",dataForForecast);
                Navigation.findNavController(this,R.id.fragment_nav_host)
                        .navigate(R.id.action_navigation_city_to_forecast,bundle);


            }
        }
    }
}