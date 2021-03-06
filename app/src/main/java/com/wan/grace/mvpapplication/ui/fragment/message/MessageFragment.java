package com.wan.grace.mvpapplication.ui.fragment.message;

import android.app.Fragment;
import android.os.Bundle;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.MVPBaseFragment;

/**
 * Created by 开发部 on 2018/2/7.
 */

public class MessageFragment extends MVPBaseFragment<MessageView,MessagePresenter> implements MessageView{

    @Override
    protected MessagePresenter createPresenter() {
        return new MessagePresenter(getActivity());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setDate(String dateStr) {

    }

    @Override
    public void setPresenter(MessagePresenter presenter) {

    }
}
