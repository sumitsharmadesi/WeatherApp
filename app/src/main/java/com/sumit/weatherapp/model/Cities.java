package com.sumit.weatherapp.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sumit.weatherapp.api.APIService;
import com.sumit.weatherapp.api.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cities {


    private String city_name;
    private String weather;
    private String city_id;

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    private MutableLiveData<List<Cities>> cities = new MutableLiveData<>();
    public MutableLiveData<List<Cities>> getCities() {
        return cities;
    }
    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
    public void fetchList(APIService apiService) {
        Cities city = new Cities();
        city.setCity_name("Pune");
        city.setWeather("25");
        List<Cities> citylist = new ArrayList<Cities>();
        citylist.add(city);
        cities.setValue(citylist);

        Observable<WeatherData > obs =  apiService.getData("Pune", ApiUtils.ApiKey,"metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<WeatherData > observer = new Observer<WeatherData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WeatherData data) {
                Log.e("data",data.getMain().getTemp().toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error","error"+e.getMessage());
            }

            @Override
            public void onComplete() {

                Log.e("model","Hello");
            }
        };
        obs.subscribe(observer);




    }
}
