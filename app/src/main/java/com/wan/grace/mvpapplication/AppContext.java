package com.wan.grace.mvpapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lqr.imagepicker.ImagePicker;
import com.lqr.imagepicker.loader.ImageLoader;
import com.lqr.imagepicker.view.CropImageView;
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
        initImagePicker();
    }

    public static AppContext getInstance() {
        return sInstance;
    }

    /**
     * 初始化仿微信控件ImagePicker
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
                Glide.with(getInstance()).load(Uri.parse("file://" + path).toString()).centerCrop().into(imageView);
            }

            @Override
            public void clearMemoryCache() {

            }
        });
        //设置图片加载器
        imagePicker.setMultiMode(false);
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
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
