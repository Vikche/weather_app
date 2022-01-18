package com.vikche.weatherforecast;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.vikche.weatherforecast.databinding.ActivityCityBinding;

public class CityActivity extends SettingBaseActivity {

    private ActivityCityBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        implementToolbar();

        NavController navController = Navigation.findNavController(this,
                R.id.nav_fragment_main);
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
    }

    private void implementToolbar() {
        Toolbar toolbar = findViewById(R.id.top_toolbar_view);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_lo);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
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
        NavigationView navigationView = findViewById(R.id.menu_nv);
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
}