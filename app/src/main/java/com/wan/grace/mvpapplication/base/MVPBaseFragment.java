package com.wan.grace.mvpapplication.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wan.grace.mvpapplication.AppContext;

import butterknife.ButterKnife;

/**
 *基础的Fragment
 *MVPBaseFragment
 *author wanchun
 *email 1596900283@qq.com
 *create 2018/3/5 14:18
 */
public abstract class MVPBaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    public AppContext ac;
    protected T mPresenter;

    private boolean mIsRequestDataRefresh = false;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ac = (AppContext) getActivity().getApplicationContext();
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(createViewLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();

    protected abstract int createViewLayoutId();

    protected void initView(View rootView) {
    }

    public Boolean isSetRefresh() {
        return true;
    }

}

