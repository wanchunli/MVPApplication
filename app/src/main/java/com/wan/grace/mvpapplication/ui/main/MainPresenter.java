package com.wan.grace.mvpapplication.ui.main;

import android.content.Context;

import com.wan.grace.mvpapplication.base.BasePresenter;

/**
 * Created by 开发部 on 2018/1/4.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private Context context;
    private MainView mainView;

    public MainPresenter(Context context) {
        this.context = context;
    }



}
