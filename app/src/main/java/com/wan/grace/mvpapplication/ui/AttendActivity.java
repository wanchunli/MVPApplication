package com.wan.grace.mvpapplication.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;
import com.wan.grace.mvpapplication.ui.presenter.AttendPresenter;
import com.wan.grace.mvpapplication.ui.view.AttendView;

import java.util.ArrayList;
import java.util.List;

import cn.aigestudio.datepicker.bizs.calendars.DPCManager;
import cn.aigestudio.datepicker.bizs.decors.DPDecor;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;

public class AttendActivity extends MVPBaseActivity<AttendView, AttendPresenter> implements AttendView {

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_attend;
    }

    @Override
    protected AttendPresenter createPresenter() {
        return new AttendPresenter(AttendActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViews() {
        super.initViews();
        //        // 默认多选模式
//        DatePicker picker = (DatePicker) findViewById(R.id.main_dp);
//        picker.setDate(2015, 7);
//        picker.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(List<String> date) {
//                String result = "";
//                Iterator iterator = date.iterator();
//                while (iterator.hasNext()) {
//                    result += iterator.next();
//                    if (iterator.hasNext()) {
//                        result += "\n";
//                    }
//                }
//                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//            }
//        });


//        // 自定义背景绘制示例 Example of custom date's background
//        List<String> tmp = new ArrayList<>();
//        tmp.add("2018-3-5");
//        tmp.add("2018-3-6");
//        tmp.add("2018-3-7");
//        tmp.add("2018-3-8");
//        tmp.add("2018-3-9");
//        tmp.add("2018-3-10");
//        tmp.add("2018-3-11");
//        DPCManager.getInstance().setDecorBG(tmp);
//
//        DatePicker picker = (DatePicker) findViewById(R.id.main_dp);
//        picker.setDate(2018, 3);
//        picker.setDPDecor(new DPDecor() {
//            @Override
//            public void drawDecorBG(Canvas canvas, Rect rect, Paint paint) {
//                paint.setColor(Color.RED);
////                canvas.drawRect(rect, paint);
//                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2F, paint);
//            }
//        });
//        picker.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(List<String> date) {
//                String result = "";
//                Iterator iterator = date.iterator();
//                while (iterator.hasNext()) {
//                    result += iterator.next();
//                    if (iterator.hasNext()) {
//                        result += "\n";
//                    }
//                }
//                Toast.makeText(AttendActivity.this, result, Toast.LENGTH_LONG).show();
//            }
//        });

//         自定义前景装饰物绘制示例 Example of custom date's foreground decor
        List<String> tmpTL = new ArrayList<>();
        tmpTL.add("2018-3-5");
        tmpTL.add("2018-3-6");
        tmpTL.add("2018-3-7");
        tmpTL.add("2018-3-8");
        tmpTL.add("2018-3-9");
        tmpTL.add("2018-3-10");
        tmpTL.add("2018-3-11");
        DPCManager.getInstance().setDecorTL(tmpTL);

        List<String> tmpTR = new ArrayList<>();
        tmpTR.add("2018-3-5");
        tmpTR.add("2018-3-6");
        tmpTR.add("2018-3-7");
        tmpTR.add("2018-3-8");
        tmpTR.add("2018-3-9");
        tmpTR.add("2018-3-10");
        tmpTR.add("2018-3-11");
        DPCManager.getInstance().setDecorTR(tmpTR);

        DatePicker picker = (DatePicker) findViewById(R.id.main_dp);
        picker.setDate(2018, 3);
        //是否显示节日
        picker.setFestivalDisplay(true);
        //是否对当天标记
        picker.setTodayDisplay(true);
        //节假日
        picker.setHolidayDisplay(false);
        //设置补休
        picker.setDeferredDisplay(false);
        picker.setMode(DPMode.NONE);
        picker.setDPDecor(new DPDecor() {
            @Override
            public void drawDecorTL(Canvas canvas, Rect rect, Paint paint, String data) {
                super.drawDecorTL(canvas, rect, paint, data);
                switch (data) {
                    case "2018-3-5":
                    case "2018-3-6":
                    case "2018-3-7":
                    case "2018-3-8":
                    case "2018-3-9":
                    case "2018-3-10":
                    case "2018-3-11":
//                        paint.setColor(Color.GRAY);
//                        canvas.drawRect(rect, paint);
                        break;
                    default:
//                        paint.setColor(Color.RED);
//                        canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, paint);
                        break;
                }
            }

            @Override
            public void drawDecorTR(Canvas canvas, Rect rect, Paint paint, String data) {
                super.drawDecorTR(canvas, rect, paint, data);
                switch (data) {
                    case "2018-3-5":
                    case "2018-3-6":
                    case "2018-3-7":
                    case "2018-3-8":

                        paint.setColor(Color.GRAY);
                        canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, paint);

                        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                        float top = fontMetrics.top;//为基线到字体上边框的距离
                        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离
                        int baseLineY = (int) (rect.centerY() - top/2 - bottom/2);//基线中间点的y轴计算公式
                        paint.setColor(Color.WHITE);
                        canvas.drawText("休", rect.centerX(), baseLineY, paint);
//                        canvas.drawText("休", rect.centerX(), rect.centerY() + rect.width() / 6, paint);
                        break;
                    case "2018-3-9":
                    case "2018-3-10":
                        paint.setColor(Color.RED);
                        canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, paint);
//                        canvas.drawRect(rect, paint);
                        Paint.FontMetrics fontMetrics1 = paint.getFontMetrics();
                        float top1 = fontMetrics1.top;//为基线到字体上边框的距离
                        float bottom1 = fontMetrics1.bottom;//为基线到字体下边框的距离
                        int baseLineY1 = (int) (rect.centerY() - top1/2 - bottom1/2);//基线中间点的y轴计算公式
                        paint.setColor(Color.WHITE);
                        canvas.drawText("迟", rect.centerX(), baseLineY1, paint);
                        break;
                    default:
                        break;
                }
            }
        });

//        picker.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(List<String> date) {
//                String result = "";
//                Iterator iterator = date.iterator();
//                while (iterator.hasNext()) {
//                    result += iterator.next();
//                    if (iterator.hasNext()) {
//                        result += "\n";
//                    }
//                }
//                Toast.makeText(AttendActivity.this, result, Toast.LENGTH_LONG).show();
//            }
//        });

        // 对话框下的DatePicker示例 Example in dialog
        Button btnPick = (Button) findViewById(R.id.main_btn);
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(AttendActivity.this).create();
                dialog.show();
                DatePicker picker = new DatePicker(AttendActivity.this);
                picker.setDate(2018, 3);
                picker.setMode(DPMode.SINGLE);
                picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
                    @Override
                    public void onDatePicked(String date) {
                        Toast.makeText(AttendActivity.this, date, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setContentView(picker, params);
                dialog.getWindow().setGravity(Gravity.CENTER);
            }
        });
    }

    @Override
    public void setDate(String dateStr) {

    }
}
