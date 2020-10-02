package com.sumit.weatherapp.viewmodel;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sumit.weatherapp.R;
import com.sumit.weatherapp.adapter.CitiesAdapter;
import com.sumit.weatherapp.api.APIService;
import com.sumit.weatherapp.api.ApiUtils;
import com.sumit.weatherapp.model.Cities;
import com.sumit.weatherapp.model.CityList;
import com.sumit.weatherapp.model.Daily;
import com.sumit.weatherapp.model.ForecastData;
import com.sumit.weatherapp.model.ForecastList;
import com.sumit.weatherapp.ui.AddCity;

import java.util.List;

public class CitiesViewModel extends ViewModel {
    private ForecastData cities;
    public MutableLiveData<Daily> selected;
    private CitiesAdapter adapter;
    public ObservableInt loading;
    public ObservableInt showEmpty;
    APIService apiService;
    AppCompatActivity activity;
    public void init(AppCompatActivity activit) {
        cities = new ForecastData();
        selected = new MutableLiveData<>();
        adapter = new CitiesAdapter(R.layout.cities_list_item, this);
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
        apiService = ApiUtils.createService(APIService.class);
        this.activity = activit;
    }

    public Daily getCityAt(Integer index) {
        if (cities.getForecastDaily().getValue() != null &&
                index != null &&
                cities.getForecastDaily().getValue().size() > index) {
            return cities.getForecastDaily().getValue().get(index);
        }
        return null;
    }

    public CitiesAdapter getAdapter() {
        return adapter;
    }


    public void onItemClick(Integer index) {
        Daily city = getCityAt(index);
        selected.setValue(city);
    }

    public void onClick() {
        Intent intent = new Intent(activity, AddCity.class);
        activity.startActivity(intent);
    }


    public MutableLiveData<List<Daily>> getCities() {
        return cities.getForecastDaily();
    }

    public void setCitiesInAdapter(List<Daily> citiess) {
        this.adapter.setCities(citiess);
        this.adapter.notifyDataSetChanged();
    }
    public void fetchList(CityList city) {
        cities.fetchForecastList(city,apiService);
    }

    public MutableLiveData<Daily> getSelected() {
        return selected;
    }
}
