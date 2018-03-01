package com.wan.grace.mvpapplication.download;

/**
 * 下载监听接口
 * DownloadStateListener
 * author wanchun
 * email 1596900283@qq.com
 * create 2018/3/1 13:49
 */
public interface DownloadStateListener {

    void onStartDownload();

    void onProgress(int progress);

    void onFinishDownload();

    void onFail(String errorInfo);

}
