package com.sumit.weatherapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Daily {

    @SerializedName("dt")
    @Expose
    private Long dt;
    @SerializedName("sunrise")
    @Expose
    private Float sunrise;
    @SerializedName("sunset")
    @Expose
    private Float sunset;
    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("pressure")
    @Expose
    private Float pressure;
    @SerializedName("humidity")
    @Expose
    private Float humidity;
    @SerializedName("dew_point")
    @Expose
    private Float dew_point;
    @SerializedName("wind_speed")
    @Expose
    private Float wind_speed;
    @SerializedName("wind_deg")
    @Expose
    private Float wind_deg;
    @SerializedName("clouds")
    @Expose
    private Float clouds;
    @SerializedName("pop")
    @Expose
    private Float pop;
    @SerializedName("uvi")
    @Expose
    private Float uvi;
    @SerializedName("rain")
    @Expose
    private Float rain;


    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Float getSunrise() {
        return sunrise;
    }

    public void setSunrise(Float sunrise) {
        this.sunrise = sunrise;
    }

    public Float getSunset() {
        return sunset;
    }

    public void setSunset(Float sunset) {
        this.sunset = sunset;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }


    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public Float getDew_point() {
        return dew_point;
    }

    public void setDew_point(Float dew_point) {
        this.dew_point = dew_point;
    }

    public Float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(Float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public Float getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(Float wind_deg) {
        this.wind_deg = wind_deg;
    }


    public Float getClouds() {
        return clouds;
    }

    public void setClouds(Float clouds) {
        this.clouds = clouds;
    }

    public Float getPop() {
        return pop;
    }

    public void setPop(Float pop) {
        this.pop = pop;
    }

    public Float getUvi() {
        return uvi;
    }

    public void setUvi(Float uvi) {
        this.uvi = uvi;
    }

    public Float getRain() {
        return rain;
    }

    public void setRain(Float rain) {
        this.rain = rain;
    }

}
