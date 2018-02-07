package com.wan.grace.mvpapplication.ui.fragment.tools;

import android.content.Context;

import com.wan.grace.mvpapplication.base.BasePresenter;

/**
 * Created by 开发部 on 2018/2/7.
 */

public class ToolsPresenter extends BasePresenter<ToolsView>{
    private Context context;
    private ToolsView platFormView;

    public ToolsPresenter(Context context) {
        this.context = context;
    }
}
