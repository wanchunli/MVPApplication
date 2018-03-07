package com.wan.grace.mvpapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lqr.imagepicker.ImagePicker;
import com.lqr.imagepicker.bean.ImageItem;
import com.lqr.imagepicker.ui.ImageGridActivity;
import com.wan.grace.mvpapplication.AppContext;
import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;
import com.wan.grace.mvpapplication.thread.ThreadPoolFactory;
import com.wan.grace.mvpapplication.ui.presenter.ScanPresenter;
import com.wan.grace.mvpapplication.ui.view.ScanView;
import com.wan.grace.mvpapplication.utils.PopupWindowUtils;
import com.wan.grace.mvpapplication.utils.UIUtils;

import java.util.ArrayList;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanActivity extends MVPBaseActivity<ScanView, ScanPresenter> implements ScanView,
        QRCodeView.Delegate, View.OnClickListener {

    public static final int IMAGE_PICKER = 100;
    private static final int PHOTO_PICKED_WITH_DATA = 3021;
    @BindView(R.id.zxingview)
    ZXingView mZxingview;
    @BindView(R.id.light_control)
    RelativeLayout lightControlLayout;
    @BindView(R.id.light_image)
    ImageView lightImageView;
    @BindView(R.id.back_layout)
    RelativeLayout backLayout;
    @BindView(R.id.more_layout)
    RelativeLayout moreLayout;
    private FrameLayout mView;
    private PopupWindow mPopupWindow;
    private AppContext ac;

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
        ac = (AppContext)getApplicationContext();
        ac.setImageMultiMode(false);
        ac.setCanCrop(true);
        ac.setCropStyle(1);
//        initToolBar(mToolbar, getString(R.string.qrcode_scan), true, true);
    }

    @Override
    public void initListener() {
        mZxingview.setDelegate(this);
        lightControlLayout.setOnClickListener(this);
        moreLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.light_control:
                if (!lightImageView.isSelected()) {
                    lightImageView.setSelected(true);
                    mZxingview.openFlashlight();
                } else {
                    lightImageView.setSelected(false);
                    mZxingview.closeFlashlight();
                }
                break;
            case R.id.more_layout:
                showPopupMenu();
                break;
            case R.id.back_layout:
                finish();
                break;
        }
    }

    private void showPopupMenu() {
        if (mView == null) {
            mView = new FrameLayout(this);
            mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mView.setBackgroundColor(UIUtils.getColor(R.color.white));

            TextView tv = new TextView(this);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, UIUtils.dip2Px(45));
            tv.setLayoutParams(params);
            tv.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            tv.setPadding(UIUtils.dip2Px(20), 0, 0, 0);
            tv.setTextColor(UIUtils.getColor(R.color.black));
            tv.setTextSize(14);
            tv.setText(UIUtils.getString(R.string.select_qr_code_from_ablum));
            mView.addView(tv);

            tv.setOnClickListener(v -> {
                mPopupWindow.dismiss();
                Intent intent = new Intent(ScanActivity.this, ImageGridActivity.class);
//                startActivityForResult(intent, IMAGE_PICKER);
                startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);

            });
        }
        mPopupWindow = PopupWindowUtils.getPopupWindowAtLocation(mView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, getWindow().getDecorView().getRootView(), Gravity.BOTTOM, 0, 0);
        mPopupWindow.setOnDismissListener(() -> PopupWindowUtils.makeWindowLight(ScanActivity.this));
        PopupWindowUtils.makeWindowDark(this);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {//返回多张照片
            if (data != null) {
                final ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size() > 0) {
                    //取第一张照片
                    ThreadPoolFactory.getNormalPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            String result = QRCodeDecoder.syncDecodeQRCode(images.get(0).path);
                            if (TextUtils.isEmpty(result)) {
//                                UIUtils.showToast(UIUtils.getString(R.string.scan_fail));
                            } else {
                                handleResult(result);
                            }
                        }
                    });
                }
            }
        }
    }
}
