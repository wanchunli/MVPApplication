package com.wan.grace.mvpapplication.ui.fragment.user;

import com.wan.grace.mvpapplication.base.BaseView;

/**
 * Created by 开发部 on 2018/2/7.
 */

public interface UserView extends BaseView<UserPresenter>{
    void setDate(String dateStr);
}
