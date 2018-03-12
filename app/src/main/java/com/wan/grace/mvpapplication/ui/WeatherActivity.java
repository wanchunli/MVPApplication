package com.wan.grace.mvpapplication.ui;

import android.Manifest;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;
import com.wan.grace.mvpapplication.bean.WeatherData;
import com.wan.grace.mvpapplication.constants.Constants;
import com.wan.grace.mvpapplication.ui.presenter.WeatherPresenter;
import com.wan.grace.mvpapplication.ui.view.WeatherView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class WeatherActivity extends MVPBaseActivity<WeatherView, WeatherPresenter> implements WeatherView {

    @BindView(R.id.weather_chart)
    LineChart weatherChart;
    private Typeface mTf;

    @Override
    protected WeatherPresenter createPresenter() {
        return new WeatherPresenter(WeatherActivity.this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        //设置toolsbar
        initToolBar(mToolbar, "", true, true);
        initMPChart();
        if (hasPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION})) {
            initLocationOption();
        } else {
            requestPermissions(Constants.ASSCESS_LOCATION_CODE, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION});
        }
    }

    @Override
    public void setDate(WeatherData data) {
        List<WeatherData.Results> list = data.results;
        Log.i("index", list.size() + "");
    }

    private void initMPChart() {
        //字体设置
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        // apply styling
        weatherChart.getDescription().setEnabled(false);
        weatherChart.setDrawGridBackground(false);

        XAxis xAxis = weatherChart.getXAxis();
        //横轴内容显示位置
        xAxis.setPosition(XAxisPosition.BOTTOM);
        //字体配置
        xAxis.setTypeface(mTf);
        //设置横轴标签数量
//        xAxis.setLabelCount(6,true);
        //是否显示横向网格线
        xAxis.setDrawGridLines(false);
        //是否显示x轴线
        xAxis.setDrawAxisLine(true);
        //配置x轴标签间隔
        xAxis.setGranularity(1f); // one hour
        //配置数值格式(当前为时间格式)
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            Calendar calendar = Calendar.getInstance();
            private SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm");

            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                Log.i("value", value + "");
                long millis = TimeUnit.HOURS.toMillis((long) value);
//                try {
//                    long millis = TimeUnit.HOURS.toMillis((long) value);
//                    calendar.setTime(mFormat.parse("0"));
//                } catch (ParseException e) {
//                    Log.i("print", e.toString());
//                    e.printStackTrace();
//                }

                return mFormat.format(new Date(millis));
            }
        });

        YAxis leftAxis = weatherChart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawZeroLine(true);
        leftAxis.setAxisMaximum(50f);
        leftAxis.setAxisMinimum(-20f);
//        leftAxis.setLabelCount(5, false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = weatherChart.getAxisRight();
        rightAxis.setTypeface(mTf);
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setLabelCount(5, false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        // set data
        weatherChart.setData(generateDataLine());
        weatherChart.animateY(1000);
        // weatherChart.animateXY(2000,500);
        // do not forget to refresh the chart
        weatherChart.invalidate();
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private LineData generateDataLine() {
        ArrayList<Entry> e1 = new ArrayList<Entry>();
        // now in hours
        long now = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis());
        float from = now;
        for (float i = 0; i < 24; i++) {
            e1.add(new Entry(i, (int) (Math.random() * 10) + 20));
        }
        LineDataSet d1 = new LineDataSet(e1, "weather_data1");
        d1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);
        d1.setDrawCircleHole(false);
        d1.setDrawCircles(false);

        ArrayList<Entry> e2 = new ArrayList<Entry>();
        for (int i = 0; i < 12; i++) {
            e2.add(new Entry(i, (int) (Math.random() * 10) + 10));
        }
        LineDataSet d2 = new LineDataSet(e2, "weather_data2");
        d2.setLineWidth(2.5f);
        d2.setCircleRadius(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);
        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
//        sets.add(d2);
        LineData cd = new LineData(sets);
        return cd;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                latitude = amapLocation.getLatitude();//获取纬度
                longitude = amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.getCountry();//国家信息
                amapLocation.getProvince();//省信息
                amapLocation.getCity();//城市信息
                amapLocation.getDistrict();//城区信息
                amapLocation.getStreet();//街道信息
                amapLocation.getStreetNum();//街道门牌号信息
                amapLocation.getCityCode();//城市编码
                amapLocation.getAdCode();//地区编码
                amapLocation.getAoiName();//获取当前定位点的AOI信息
                amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                amapLocation.getFloor();//获取当前室内定位的楼层
                amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                //获取定位时间
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);
                Toast.makeText(WeatherActivity.this, latitude + "," + longitude, Toast.LENGTH_SHORT).show();
                mPresenter.loadWeather(latitude, longitude);
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

}
