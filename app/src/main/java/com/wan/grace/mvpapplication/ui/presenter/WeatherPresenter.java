package com.wan.grace.mvpapplication.ui.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.wan.grace.mvpapplication.AppContext;
import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.api.WeatherApi;
import com.wan.grace.mvpapplication.base.BasePresenter;
import com.wan.grace.mvpapplication.bean.WeatherData;
import com.wan.grace.mvpapplication.ui.main.MainView;
import com.wan.grace.mvpapplication.ui.view.ScanView;
import com.wan.grace.mvpapplication.ui.view.WeatherView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 开发部 on 2018/2/8.
 */

public class WeatherPresenter extends BasePresenter<WeatherView> {

    private Context context;
    private WeatherView weatherView;

    public WeatherPresenter(Context context) {
        this.context = context;
    }

    public void loadWeather(double latitude, double longitude) {
        String location = longitude + "," + latitude;
//        String location = "113.8555210000,22.6334480000";
        weatherView = getView();
        if (weatherView != null) {
            weatherApi.getDetailWeather(location)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(weatherInfo -> {
                        displayWeather(context, weatherInfo, weatherView);
                    }, this::loadError);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.app_name, Toast.LENGTH_SHORT).show();
    }

    private void displayWeather(Context context, WeatherData weatherData, WeatherView weatherView) {
        weatherView.setDate(weatherData);
    }

}
