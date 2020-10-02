package com.sumit.weatherapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sumit.weatherapp.R;
import com.sumit.weatherapp.api.APIService;
import com.sumit.weatherapp.api.ApiUtils;
import com.sumit.weatherapp.databinding.ActivityMainBinding;
import com.sumit.weatherapp.databinding.AddCityBinding;
import com.sumit.weatherapp.model.Cities;
import com.sumit.weatherapp.model.CityList;
import com.sumit.weatherapp.model.MainData;
import com.sumit.weatherapp.viewmodel.AddCityViewModel;
import com.sumit.weatherapp.viewmodel.CitiesViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddCity extends AppCompatActivity {
    AddCityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBindings(savedInstanceState);


    }
    private void setupBindings(Bundle savedInstanceState) {
        AddCityBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.add_city);
        viewModel = ViewModelProviders.of(this).get(AddCityViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setModel(viewModel);
        setupListUpdate();

    }


    private void setupListUpdate() {
        viewModel.loading.set(View.VISIBLE);
        String s = loadJSONFromAsset(this);
        Log.e("cities",""+s);
        List<CityList> cityLists = new Gson().fromJson(s, new TypeToken<List<CityList>>() {
        }.getType());
        viewModel.fetchList(cityLists);
        viewModel.getCities().observe(this, new Observer<List<CityList>>() {
            @Override
            public void onChanged(List<CityList> cities) {
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
        viewModel.getSelected().observe(this, new Observer<CityList>() {
            @Override
            public void onChanged(CityList cities) {
                if (cities != null) {
                    Intent intent = new Intent(AddCity.this,ForecastActivity.class);
                    intent.putExtra("city",cities);
                    startActivity(intent);
                }
            }
        });
    }



    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("city_list.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
//            ex.printStackTrace();
            return null;
        }
        return json;

    }

}
