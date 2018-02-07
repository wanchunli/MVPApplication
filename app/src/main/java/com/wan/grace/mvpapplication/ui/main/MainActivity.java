package com.wan.grace.mvpapplication.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;

public class MainActivity extends MVPBaseActivity<MainView,MainPresenter> implements MainView{



    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(MainActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setDate(String dateStr) {

    }

}
