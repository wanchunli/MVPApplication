package com.wan.grace.mvpapplication.ui.fragment.user;

import android.os.Bundle;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.MVPBaseFragment;

/**
 * Created by 开发部 on 2018/2/7.
 */

public class UserFragment extends MVPBaseFragment<UserView,UserPresenter> implements UserView {

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(getActivity());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setDate(String dateStr) {

    }

    @Override
    public void setPresenter(UserPresenter presenter) {

    }
}