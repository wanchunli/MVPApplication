package com.wan.grace.mvpapplication.api;

/**
 * Api工厂类
 * ApiFactory
 * author wanchun
 * email 1596900283@qq.com
 * create 2018/3/5 14:30
 */
public class ApiFactory {

    protected static final Object monitor = new Object();
    static MainApi mainApiSingleton = null;

    //return Singleton
    public static MainApi getMainApiSingleton() {
        synchronized (monitor) {
            if (mainApiSingleton == null) {
                mainApiSingleton = new ApiRetrofit().getMainApiService();
            }
            return mainApiSingleton;
        }
    }

}
