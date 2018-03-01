package com.wan.grace.mvpapplication.manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.FileUriExposedException;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.webkit.DownloadListener;

import com.wan.grace.mvpapplication.api.ApiFactory;
import com.wan.grace.mvpapplication.api.DownloadApi;
import com.wan.grace.mvpapplication.api.MainApi;
import com.wan.grace.mvpapplication.download.DownloadInterceptor;
import com.wan.grace.mvpapplication.download.DownloadStateListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * App更新管理
 * UpdateManager
 * author wanchun
 * email 1596900283@qq.com
 * create 2018/3/1 12:34
 */
public class UpdateManager {

    private static final String TAG = "UpdateManager";
    private static final int DEFAULT_TIMEOUT = 15;
    private Retrofit retrofit;
    private DownloadStateListener listener;
    private String baseUrl = "http://gdown.baidu.com/data/wisegame/16f98e07f392294b/";
    private Context context;
    public static final String APK_NAME = "QQ_794";
    public static final String APK_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath() + "/APK/";
    public static final String APK_PATH = APK_FOLDER + APK_NAME + ".apk";

    public UpdateManager(Context context) {
        this.context = context;
        this.listener = (DownloadStateListener) context;
        DownloadInterceptor mInterceptor = new DownloadInterceptor(listener);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * 开始下载
     * download
     * @param url
     * @param subscriber
     */
    public void download(@NonNull String url, Subscriber subscriber) {
        listener.onStartDownload();
        // subscribeOn()改变调用它之前代码的线程
        // observeOn()改变调用它之后代码的线程
        retrofit.create(DownloadApi.class)
                .download(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, InputStream>() {

                    @Override
                    public InputStream call(ResponseBody responseBody) {
                        return responseBody.byteStream();
                    }
                })
                .observeOn(Schedulers.computation()) // 用于计算任务
                .doOnNext(new Action1<InputStream>() {
                    @Override
                    public void call(InputStream inputStream) {
                        writeFile(inputStream, APK_FOLDER);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 将输入流写入文件
     * @param inputString
     * @param savePath
     */
    private void writeFile(InputStream inputString, String savePath) {
        String tempFileName = APK_NAME + ".tmp";
        String apkFileName = APK_NAME + ".apk";
        String tempPath = savePath + tempFileName;
        String apkPath = savePath + apkFileName;
        File saveFolder = new File(savePath);
        if (!saveFolder.exists()) {
            saveFolder.mkdirs();
        }
        // 输出临时下载文件
        File tempFile = new File(tempPath);
        File apkFile = new File(apkPath);
        if (apkFile.exists()) {
            apkFile.delete();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(tempFile);
            byte[] b = new byte[1024];
            int len;
            while ((len = inputString.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            inputString.close();
            tempFile.renameTo(apkFile);
            fos.close();
        } catch (FileNotFoundException e) {
            listener.onFail("FileNotFoundException");
        } catch (IOException e) {
            listener.onFail("IOException");
        }
    }

    /**
     * 安装apk
     */
    public void installApk() {
        File apkFile = new File(APK_PATH);
        if (!apkFile.exists()) {
            return;
        }
        Intent installApkIntent = new Intent();
        installApkIntent.setAction(Intent.ACTION_VIEW);
        installApkIntent.addCategory(Intent.CATEGORY_DEFAULT);
        installApkIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            Uri photoURI = FileProvider.getUriForFile(context,
                    context.getApplicationContext().getPackageName() + ".provider"
                    , apkFile);
            installApkIntent.setDataAndType(photoURI, "application/vnd.android.package-archive");
            installApkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            installApkIntent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        if (context.getPackageManager().queryIntentActivities(installApkIntent, 0).size() > 0) {
            context.startActivity(installApkIntent);
        }
    }

    /**
     * 安装apk
     */
    public Intent getIntentInstallApk() {
        File apkFile = new File(APK_PATH);
        if (!apkFile.exists()) {
            return new Intent();
        }
        Intent installApkIntent = new Intent();
        installApkIntent.setAction(Intent.ACTION_VIEW);
        installApkIntent.addCategory(Intent.CATEGORY_DEFAULT);
        installApkIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            Uri photoURI = FileProvider.getUriForFile(context,
                    context.getApplicationContext().getPackageName() + ".provider"
                    , apkFile);
            installApkIntent.setDataAndType(photoURI, "application/vnd.android.package-archive");
            installApkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            installApkIntent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        if (context.getPackageManager().queryIntentActivities(installApkIntent, 0).size() > 0) {
            context.startActivity(installApkIntent);
            return installApkIntent;
        }
        return installApkIntent;
    }

}


