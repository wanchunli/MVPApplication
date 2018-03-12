package com.wan.grace.mvpapplication.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.BasePresenter;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;
import com.wan.grace.mvpapplication.constants.Constants;
import com.wan.grace.mvpapplication.ui.main.MainActivity;

public class SplashActivity extends MVPBaseActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!hasPermissions(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            requestPermissions(Constants.ASSCESS_LOCATION_CODE, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!hasPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestPermissions(Constants.WRITE_EXTERNAL_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        Intent it = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(it);
        finish();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void initViews() {
        super.initViews();
    }


}
