package com.wan.grace.mvpapplication.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * app下载
 * DownloadApi
 * author wanchun
 * email 1596900283@qq.com
 * create 2018/3/1 16:34
 */
public interface DownloadApi {

    /**
     * 下载apk文件
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
