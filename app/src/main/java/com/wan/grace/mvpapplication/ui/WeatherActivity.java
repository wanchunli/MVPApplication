package com.wan.grace.mvpapplication.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

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
import com.wan.grace.mvpapplication.ui.presenter.WeatherPresenter;
import com.wan.grace.mvpapplication.ui.view.WeatherView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class WeatherActivity extends MVPBaseActivity<WeatherView, WeatherPresenter> implements WeatherView{

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
    public void init(){
        //设置toolsbar
        initToolBar(mToolbar, "", true, true);
        //字体设置
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        // apply styling
        weatherChart.getDescription().setEnabled(false);
        weatherChart.setDrawGridBackground(false);

        XAxis xAxis = weatherChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setLabelCount(5,true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm");

            @Override
            public String getFormattedValue(float value, AxisBase axis) {

//                long millis = TimeUnit.HOURS.toMillis((long) value);
//                long hours = TimeUnit.HOURS.toHours((long) value);
                long hours = TimeUnit.HOURS.toHours((long) 0.00f);
                return mFormat.format(new Date(hours));
            }
        });

        YAxis leftAxis = weatherChart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setDrawGridLines(false);
        leftAxis.setLabelCount(5, false);
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
        weatherChart.animateX(750);
        // do not forget to refresh the chart
        // holder.chart.invalidate();
    }

    @Override
    public void setDate(String dateStr) {

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
            e1.add(new Entry(i, (int) (Math.random() * 10) + 10));
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

}
