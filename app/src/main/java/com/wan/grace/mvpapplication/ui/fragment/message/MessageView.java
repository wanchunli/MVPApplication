package com.wan.grace.mvpapplication.ui.fragment.message;

import com.wan.grace.mvpapplication.base.BaseView;

/**
 * Created by 开发部 on 2018/2/7.
 */

public interface MessageView extends BaseView<MessagePresenter>{
    void setDate(String dateStr);
}
