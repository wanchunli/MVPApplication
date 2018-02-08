package com.wan.grace.mvpapplication.ui.main;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.BasePresenter;
import com.wan.grace.mvpapplication.utils.AnimUtil;

/**
 * Created by 开发部 on 2018/1/4.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private Context context;
    private MainView mainView;

    public MainPresenter(Context context) {
        this.context = context;
    }

}
