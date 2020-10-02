package com.sumit.weatherapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sumit.weatherapp.R;
import com.sumit.weatherapp.databinding.ActivityMainBinding;
import com.sumit.weatherapp.model.Cities;
import com.sumit.weatherapp.model.CityList;
import com.sumit.weatherapp.model.Daily;
import com.sumit.weatherapp.model.ForecastData;
import com.sumit.weatherapp.model.ForecastList;
import com.sumit.weatherapp.viewmodel.CitiesViewModel;

import java.util.List;

public class ForecastActivity extends AppCompatActivity {

    private CitiesViewModel viewModel;
    CityList city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        city = (CityList)getIntent().getSerializableExtra("city");
        setupBindings(savedInstanceState);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    private void setupBindings(Bundle savedInstanceState) {
        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(CitiesViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init(this);
        }
        activityBinding.setModel(viewModel);
        setupListUpdate();

    }

    private void setupListUpdate() {
        viewModel.loading.set(View.VISIBLE);
        viewModel.fetchList(city);
        viewModel.getCities().observe(this, new Observer<List<Daily>>() {
            @Override
            public void onChanged(List<Daily> cities) {
                viewModel.loading.set(View.GONE);
                if (cities.size() == 0) {
                    viewModel.showEmpty.set(View.VISIBLE);
                } else {
                    viewModel.showEmpty.set(View.GONE);
                    viewModel.setCitiesInAdapter(cities);
                }
            }
        });
        setupListClick();
    }

    private void setupListClick() {
        viewModel.getSelected().observe(this, new Observer<Daily>() {
            @Override
            public void onChanged(Daily cities) {
                if (cities != null) {
                    Toast.makeText(ForecastActivity.this, "You selected " +cities.getTemp().getMax(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}