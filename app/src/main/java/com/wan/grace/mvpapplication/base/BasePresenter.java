package com.wan.grace.mvpapplication.base;

import com.wan.grace.mvpapplication.AppContext;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 基础Presenter
 * BasePresenter
 * author wanchun
 * email 1596900283@qq.com
 * create 2018/3/5 14:17
 */
public abstract class BasePresenter<V> {

    protected Reference<V> mViewRef;

    public AppContext ac;

//    public static final MainApi mainApi = ApiFactory.getMainApiSingleton();
//
//    public static final NetPlayApi netPlayApi = ApiFactory.getNetPlayApiSingleton();

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}