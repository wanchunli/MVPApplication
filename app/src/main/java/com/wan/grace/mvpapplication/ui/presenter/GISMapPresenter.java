package com.wan.grace.mvpapplication.ui.presenter;

import android.content.Context;

import com.wan.grace.mvpapplication.base.BasePresenter;
import com.wan.grace.mvpapplication.ui.view.GISMapView;
import com.wan.grace.mvpapplication.ui.view.ScanView;

/**
 * Created by 开发部 on 2018/2/8.
 */

public class GISMapPresenter extends BasePresenter<GISMapView> {

    private Context context;
    private GISMapView gisMapView;

    public GISMapPresenter(Context context) {
        this.context = context;
    }
}
