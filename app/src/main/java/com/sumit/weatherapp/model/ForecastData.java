package com.sumit.weatherapp.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sumit.weatherapp.api.APIService;
import com.sumit.weatherapp.api.ApiUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ForecastData {
    @SerializedName("lat")
    @Expose
    private Float lat;
    @SerializedName("lon")
    @Expose
    private Float lon;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("timezone_offset")
    @Expose
    private Integer timezone_offset;
    @SerializedName("daily")
    @Expose
    private List<Daily> daily = null;

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getTimezone_offset() {
        return timezone_offset;
    }

    public void setTimezone_offset(Integer timezone_offset) {
        this.timezone_offset = timezone_offset;
    }


    public List<Daily> getDaily() {
        return daily;
    }

    public void setDaily(List<Daily> daily) {
        this.daily = daily;
    }
    private MutableLiveData<List<Daily>> cities = new MutableLiveData<>();
    public MutableLiveData<List<Daily>> getForecastDaily() {
        return cities;
    }
    public void fetchForecastList(CityList city, APIService apiService){
        Observable<ForecastData > obs =  apiService.getForecastData(city.getCoord().getLat().toString(),city.getCoord().getLon().toString(),"hourly,minutely", ApiUtils.ApiKey,"metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<ForecastData> observer = new Observer<ForecastData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ForecastData data) {
                for(int i=0;i<data.daily.size();i++){
                    Daily daily = data.daily.get(i);
                    try{
                        long dattt = daily.getDt();
                        Date theDate = new Date(dattt*1000L);
                        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                        daily.setDate(format.format(theDate));
                        Log.e("date",""+format.format(theDate));
                    }catch (Exception e){
                        Log.e("error",""+e.getMessage());
                    }
                }
                cities.setValue(data.daily);
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