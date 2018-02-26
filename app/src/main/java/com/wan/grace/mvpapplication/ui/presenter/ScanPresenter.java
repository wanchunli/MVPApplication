package com.wan.grace.mvpapplication.ui.presenter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.wan.grace.mvpapplication.base.BasePresenter;
import com.wan.grace.mvpapplication.bean.ContactBean;
import com.wan.grace.mvpapplication.ui.view.ContactView;
import com.wan.grace.mvpapplication.ui.view.ScanView;
import com.wan.grace.mvpapplication.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 开发部 on 2018/2/8.
 */

public class ScanPresenter extends BasePresenter<ScanView> {

    private Context context;
    private ScanView scanView;

    public ScanPresenter(Context context) {
        this.context = context;
    }
}
