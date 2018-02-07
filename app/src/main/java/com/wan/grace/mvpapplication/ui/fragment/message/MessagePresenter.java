package com.wan.grace.mvpapplication.ui.fragment.message;

import android.content.Context;

import com.wan.grace.mvpapplication.base.BasePresenter;

/**
 * Created by 开发部 on 2018/2/7.
 */

public class MessagePresenter extends BasePresenter<MessageView>{
    private Context context;
    private MessageView messageView;

    public MessagePresenter(Context context) {
        this.context = context;
    }
}
