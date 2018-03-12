package com.wan.grace.mvpapplication.api;

import com.wan.grace.mvpapplication.bean.WeatherData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 开发部 on 2018/3/8.
 */

public interface WeatherApi {

    public static final String WEATHER_TAG = "&output=json&ak=KfbSjSdHgX838fY8aQyKSblQDclZKMOf";

    @GET("weather?" + WEATHER_TAG)
    Observable<WeatherData> getDetailWeather(@Query("location") String location);
}
