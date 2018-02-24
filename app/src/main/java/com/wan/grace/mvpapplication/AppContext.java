package com.wan.grace.mvpapplication;

import android.app.Application;

/**
 * Created by 开发部 on 2018/2/8.
 */

public class AppContext extends Application {

//    private static volatile AppContext singleton;
    private static AppContext sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static AppContext getInstance() {
        return sInstance;
    }

//    public static AppContext getInstance() {
//        if (singleton == null) {
//            synchronized (AppContext.class) {
//                if (singleton == null) {
//                    singleton = new AppContext();
//                }
//            }
//        }
//        return singleton;
//    }

}
