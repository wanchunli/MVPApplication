package com.wan.grace.mvpapplication.ui.fragment.tools;

import android.os.Bundle;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.MVPBaseFragment;

/**
 * Created by 开发部 on 2018/2/7.
 */

public class ToolsFragment extends MVPBaseFragment<ToolsView,ToolsPresenter> implements ToolsView {

    @Override
    protected ToolsPresenter createPresenter() {
        return new ToolsPresenter(getActivity());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_tools;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setDate(String dateStr) {

    }

    @Override
    public void setPresenter(ToolsPresenter presenter) {

    }
}