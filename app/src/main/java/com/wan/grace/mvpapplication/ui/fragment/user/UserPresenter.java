package com.wan.grace.mvpapplication.ui.fragment.user;

import android.content.Context;

import com.wan.grace.mvpapplication.base.BasePresenter;

/**
 * Created by 开发部 on 2018/2/7.
 */

public class UserPresenter extends BasePresenter<UserView>{
    private Context context;
    private UserView platFormView;

    public UserPresenter(Context context) {
        this.context = context;
    }
}
