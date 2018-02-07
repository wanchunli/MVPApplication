package com.wan.grace.mvpapplication.ui.fragment.platform;

import android.content.Context;

import com.wan.grace.mvpapplication.base.BasePresenter;
import com.wan.grace.mvpapplication.ui.fragment.message.MessageView;

/**
 * Created by 开发部 on 2018/2/7.
 */

public class PlatFormPresenter extends BasePresenter<PlatFormView>{
    private Context context;
    private PlatFormView platFormView;

    public PlatFormPresenter(Context context) {
        this.context = context;
    }
}
