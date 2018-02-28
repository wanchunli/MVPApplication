package com.wan.grace.mvpapplication;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wan.grace.mvpapplication.bean.Movie;
import com.wan.grace.mvpapplication.bean.MovieSubject;
import com.wan.grace.mvpapplication.bean.User;
import com.wan.grace.mvpapplication.cache.AppShared;

import java.util.List;

/**
 * Created by 开发部 on 2018/2/8.
 */

public class AppContext extends Application {

    private static AppContext sInstance;
    private AppShared mAppShared;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mAppShared = new AppShared(this);
    }

    public static AppContext getInstance() {
        return sInstance;
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
    }

    public AppShared getAppShared() {
        if (mAppShared == null) {
            mAppShared = new AppShared(this);
        }
        return mAppShared;
    }

    public boolean isLogin() {
        if (mAppShared == null) {
            mAppShared = new AppShared(this);
        }
        return mAppShared.hasUserInfo();
    }

    public User getUserInfo() {
        if (mAppShared == null) {
            mAppShared = new AppShared(this);
        }
        return mAppShared.getUserInfo();
    }

    public void saveUserInfo(User user) {
        if (mAppShared == null) {
            mAppShared = new AppShared(this);
        }
        mAppShared.setUserInfo(user);
    }

    public void clearUserInfo() {
        if (mAppShared == null) {
            mAppShared = new AppShared(this);
        }
        mAppShared.clearUserInfo();
    }

    public void clearUserArray() {
        if (mAppShared == null) {
            mAppShared = new AppShared(this);
        }
        mAppShared.clearUserArray();
    }

    public MovieSubject getBannerData() {
        if (mAppShared == null) {
            mAppShared = new AppShared(this);
        }
        return mAppShared.getBannerData();
    }

    public void saveBannerData(MovieSubject movieSubject) {
        if (mAppShared == null) {
            mAppShared = new AppShared(this);
        }
        mAppShared.saveBannerData(movieSubject);
    }

    /**
     * 检测网络是否可用
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }
}
