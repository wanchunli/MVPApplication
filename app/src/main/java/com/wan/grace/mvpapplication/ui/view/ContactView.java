package com.wan.grace.mvpapplication.ui.view;

import com.wan.grace.mvpapplication.bean.ContactBean;

import java.util.List;

/**
 * Created by 开发部 on 2018/2/8.
 */

public interface ContactView {
    void setDate(String dateStr);
    void setList(String tagsStr,List<ContactBean> list);
}
