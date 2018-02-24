package com.wan.grace.mvpapplication.ui.fragment.platform;

import com.wan.grace.mvpapplication.base.BaseView;
import com.wan.grace.mvpapplication.bean.MovieSubject;

/**
 * Created by 开发部 on 2018/2/7.
 */

public interface PlatFormView extends BaseView<PlatFormPresenter>{
    void setDate(String dateStr, MovieSubject movieSubject);
}
