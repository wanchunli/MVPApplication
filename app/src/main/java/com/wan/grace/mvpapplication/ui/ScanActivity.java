package com.wan.grace.mvpapplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;
import com.wan.grace.mvpapplication.ui.presenter.ScanPresenter;
import com.wan.grace.mvpapplication.ui.view.ScanView;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanActivity extends MVPBaseActivity<ScanView, ScanPresenter> implements ScanView,
        QRCodeView.Delegate {

    @BindView(R.id.zxingview)
    ZXingView mZxingview;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_scan;
    }

    @Override
    protected ScanPresenter createPresenter() {
        return new ScanPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViews() {
        super.initViews();
        initToolBar(mToolbar, getString(R.string.qrcode_scan), true, true);
    }

    @Override
    public void initListener() {
        mZxingview.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mZxingview.startCamera();
        mZxingview.startSpotAndShowRect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mZxingview.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mZxingview.onDestroy();
    }

    @Override
    public void setDate(String dateStr) {

    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        handleResult(result);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        showTips(getString(R.string.open_camera_failed));
    }

    private void handleResult(String result) {
        vibrate();
        mZxingview.startSpot();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
