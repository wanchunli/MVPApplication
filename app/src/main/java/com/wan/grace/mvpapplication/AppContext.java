package com.wan.grace.mvpapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lqr.imagepicker.ImagePicker;
import com.lqr.imagepicker.loader.ImageLoader;
import com.lqr.imagepicker.view.CropImageView;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;
import com.wan.grace.mvpapplication.bean.Movie;
import com.wan.grace.mvpapplication.bean.MovieSubject;
import com.wan.grace.mvpapplication.bean.User;
import com.wan.grace.mvpapplication.cache.AppShared;
import com.wan.grace.mvpapplication.utils.NetUtil;

import java.util.List;

/**
 * Created by 开发部 on 2018/2/8.
 */

public class AppContext extends Application {

    private static AppContext sInstance;
    private AppShared mAppShared;
    private ImagePicker imagePicker = ImagePicker.getInstance();
    /**
     * 网络类型
     */
    private int netMobile;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mAppShared = new AppShared(this);
        initImagePicker();
//        netListener();
    }

    public static AppContext getInstance() {
        return sInstance;
    }

    /**
     * 初始化仿微信控件ImagePicker
     */
    private void initImagePicker() {

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
        imagePicker.setMultiMode(true);
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

    /**
     * 动态设置图片选择器的单选与多选模式
     *
     * @param bool
     */
    public void setImageMultiMode(boolean bool) {
        imagePicker.setMultiMode(bool);
    }

    /**
     * 动态设置图片选择器是否可剪裁
     *
     * @param bool
     */
    public void setCanCrop(boolean bool) {
        imagePicker.setCrop(bool);
    }

    /**
     * 动态设置图片选择器剪裁形状
     *
     * @param cropFlag
     */
    public void setCropStyle(int cropFlag) {
        switch (cropFlag) {
            case 0:
                imagePicker.setStyle(CropImageView.Style.RECTANGLE);
                break;
            case 1:
                imagePicker.setStyle(CropImageView.Style.CIRCLE);
                break;
            default:
                imagePicker.setStyle(CropImageView.Style.RECTANGLE);
                break;
        }

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

    int flag = 0;

    public void netListener() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        connectivityManager.requestNetwork(new NetworkRequest.Builder().build(),
                new ConnectivityManager.NetworkCallback() {
                    @Override
                    public void onAvailable(Network network) {
                        super.onAvailable(network);
                        if (flag != 0) {
                            netMobile = NetUtil.getNetWorkState(getApplicationContext());
                            if (netMobile == 1) {
                                Toast.makeText(getApplicationContext(), getString(R.string.wifi_connected), Toast.LENGTH_SHORT).show();
//                                showTips(getString(R.string.wifi_connected));
                            } else if (netMobile == 0) {
                                Toast.makeText(getApplicationContext(), getString(R.string.wifi_connected), Toast.LENGTH_SHORT).show();
//                                showTips(getString(R.string.mobile_connected));
                            } else if (netMobile == -1) {
                                Toast.makeText(getApplicationContext(), getString(R.string.wifi_connected), Toast.LENGTH_SHORT).show();
//                                showTips(getString(R.string.no_net_connected));
                            }
                        }
                        flag = 1;
                    }
                });
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
