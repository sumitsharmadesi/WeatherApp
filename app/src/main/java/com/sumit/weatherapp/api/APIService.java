package com.sumit.weatherapp.api;

import com.google.gson.JsonObject;
import com.sumit.weatherapp.model.ForecastData;
import com.sumit.weatherapp.model.MainData;
import com.sumit.weatherapp.model.WeatherData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {


    @Headers( "Content-Type: application/json" )
    @GET("weather?")
    Observable<WeatherData> getData(@Query("q") String city,
                                    @Query("appid") String apikey,@Query("units")String unit);
    @GET("group")
    Observable<MainData> getListData(@Query("id")String ids, @Query("appid")String apikey, @Query("units")String unit);

    @GET("onecall")
    Observable<ForecastData> getForecastData(@Query("lat")String lat, @Query("lon")String lon,@Query("exclude")String exc,@Query("appid")String apikey,@Query("units")String unit);

}
