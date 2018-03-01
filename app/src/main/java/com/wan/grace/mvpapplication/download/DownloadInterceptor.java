package com.wan.grace.mvpapplication.download;

import android.webkit.DownloadListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 拦截器
 * DownloadInterceptor
 * author wanchun
 * email 1596900283@qq.com
 * create 2018/3/1 13:57
 */
public class DownloadInterceptor implements Interceptor {

    private DownloadStateListener downloadStateListener;

    public DownloadInterceptor(DownloadStateListener downloadStateListener) {
        this.downloadStateListener = downloadStateListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(
                new DownloadResponseBody(response.body(), downloadStateListener)).build();
    }
}
