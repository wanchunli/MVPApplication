package com.wan.grace.mvpapplication.ui.presenter;

import android.content.Context;

import com.wan.grace.mvpapplication.base.BasePresenter;
import com.wan.grace.mvpapplication.ui.view.ScanView;
import com.wan.grace.mvpapplication.ui.view.WeatherView;

/**
 * Created by 开发部 on 2018/2/8.
 */

public class WeatherPresenter extends BasePresenter<WeatherView> {

    private Context context;
    private WeatherView weatherView;

    public WeatherPresenter(Context context) {
        this.context = context;
    }
}
