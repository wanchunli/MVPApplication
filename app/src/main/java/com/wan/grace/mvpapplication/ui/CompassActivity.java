package com.wan.grace.mvpapplication.ui;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.BasePresenter;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;
import com.wan.grace.mvpapplication.view.ChaosCompassView;

import butterknife.BindView;
/**
 * Compass 指南针
 */
public class CompassActivity extends MVPBaseActivity {
    private SensorManager mSensorManager;
    private SensorEventListener mSensorEventListener;

    @BindView(R.id.ccv)
    private ChaosCompassView chaosCompassView;
    private float val;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_compass;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initViews() {
        super.initViews();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                val = event.values[0];
                chaosCompassView.setVal(val);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(mSensorEventListener);
    }
}
