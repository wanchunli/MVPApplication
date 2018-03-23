package com.wan.grace.mvpapplication.ui.fragment.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.MVPBaseFragment;
import com.wan.grace.mvpapplication.ui.CompassActivity;
import com.wan.grace.mvpapplication.ui.WeatherActivity;

import butterknife.BindView;

/**
 * Created by 开发部 on 2018/2/7.
 */

public class ToolsFragment extends MVPBaseFragment<ToolsView,ToolsPresenter>
        implements ToolsView,View.OnClickListener {

    @BindView(R.id.compass_layout)
    RelativeLayout compassLayout;
    @BindView(R.id.note_layout)
    RelativeLayout noteLayout;
    @BindView(R.id.weather_layout)
    RelativeLayout weatherLayout;

    @Override
    protected ToolsPresenter createPresenter() {
        return new ToolsPresenter(getActivity());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_tools;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View rootView) {
        compassLayout.setOnClickListener(this);
        noteLayout.setOnClickListener(this);
        weatherLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.compass_layout:
                Intent it_compass = new Intent(getActivity(), CompassActivity.class);
                startActivity(it_compass);
                break;
            case R.id.note_layout:
                Intent it_note = new Intent(getActivity(), CompassActivity.class);
                startActivity(it_note);
                break;
            case R.id.weather_layout:
                Intent it_weather = new Intent(getActivity(), WeatherActivity.class);
                startActivity(it_weather);
                break;
        }
    }

    @Override
    public void setDate(String dateStr) {

    }

    @Override
    public void setPresenter(ToolsPresenter presenter) {

    }
}