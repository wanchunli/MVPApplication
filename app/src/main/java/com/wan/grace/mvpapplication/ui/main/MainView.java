package com.wan.grace.mvpapplication.ui.main;

import com.wan.grace.mvpapplication.base.BaseView;

/**
 * Created by 开发部 on 2018/1/4.
 */

public interface MainView extends BaseView<MainPresenter>{
    void setDate(String dateStr);
}
