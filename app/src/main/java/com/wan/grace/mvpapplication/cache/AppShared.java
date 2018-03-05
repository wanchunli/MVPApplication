package com.wan.grace.mvpapplication.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.wan.grace.mvpapplication.bean.Movie;
import com.wan.grace.mvpapplication.bean.MovieSubject;
import com.wan.grace.mvpapplication.bean.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

/**
 *共享存储类
 *AppShared
 *author wanchun
 *email 1596900283@qq.com
 *create 2018/3/5 14:22
 */
public class AppShared {

    public static final String SHARED_NAME = "ANSO";

    public static final String KEY_IS_FIRST_BOOT = "is_first_boot";

    public static final String KEY_IS_REMEMBER = "is_remember";
    public static final String KEY_IS_AUTOLOGIN = "is_autologin";

    //Banner图片缓存
    public static final String KEY_BANNER_DATA = "banner_data";


    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_PASSWORD = "user_password";
    public static final String KEY_USER_FULLNAME = "user_fullname";
    public static final String KEY_USER_SEX = "user_sex";
    public static final String KEY_USER_BIRTHDAY = "user_birthday";
    public static final String KEY_USER_MOBILE = "user_mobile";
    public static final String KEY_USER_IDCARD = "user_idcard";
    public static final String KEY_USER_COMPANYID = "user_companyId";
    public static final String KEY_USER_DEPARTMENTID = "user_depatrmentId";
    public static final String KEY_USER_JOBID = "user_jobId";
    public static final String KEY_USER_OPERATORID = "operatorid";
    public static final String KEY_USER_SIGNATURE = "signature";
    public static final String KEY_USER_CREATE_TIME = "createTimeStr";
    public static final String KEY_USER_UPDATE_TIME = "updateTimeStr";
    public static final String KEY_USER_ARRAY = "array";

    public static final String KEY_USER_LOGINID = "loginId";
    public static final String KEY_USER_REMEMBERME = "isRememberMe";
    public static final String KEY_USER_COMPANY = "company";
    public static final String KEY_USER_NAVIGATION = "isNavigation";
    public static final String KEY_USER_Pricinctname = "pricinctname";

    private SharedPreferences mPreference;

    public AppShared(Context context) {
        mPreference = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 设置第一次启动
     */
    public void setAutoLogin(boolean flag) {
        mPreference.edit().putBoolean(KEY_IS_AUTOLOGIN, flag).commit();
    }

    public boolean isAutoLogin() {
        return mPreference.getBoolean(KEY_IS_AUTOLOGIN, true);
    }

    /**
     * 设置记住密码
     */
    public void setRemember(boolean flag) {
        mPreference.edit().putBoolean(KEY_IS_REMEMBER, flag).commit();
    }

    public boolean isRemember() {
        return mPreference.getBoolean(KEY_IS_REMEMBER, true);
    }

    /**
     * 设置自动登录
     */
    public void setFirstBoot(boolean flag) {
        mPreference.edit().putBoolean(KEY_IS_FIRST_BOOT, flag).commit();
    }

    public boolean isFirstBoot() {
        return mPreference.getBoolean(KEY_IS_FIRST_BOOT, true);
    }

    /**
     * 保存用户信息
     */
    public void setUserInfo(User user) {
        mPreference.edit().putString(KEY_USER_ID, user.getId()).commit();
        mPreference.edit().putString(KEY_USER_NAME, user.getUsername()).commit();
        mPreference.edit().putString(KEY_USER_PASSWORD, user.getPwd()).commit();
        mPreference.edit().putString(KEY_USER_ARRAY, user.getArray()).commit();
        mPreference.edit().putString(KEY_USER_LOGINID, user.getLoginId()).commit();
        mPreference.edit().putString(KEY_USER_COMPANY, user.getCompanyname()).commit();
        mPreference.edit().putBoolean(KEY_IS_AUTOLOGIN, user.isAutoLogin()).commit();
        mPreference.edit().putBoolean(KEY_USER_NAVIGATION, user.isNavigation()).commit();
        mPreference.edit().putString(KEY_USER_Pricinctname, user.getPricinctname()).commit();
    }

    /**
     * 清除用户信息
     */
    public void clearUserInfo() {
        mPreference.edit().remove(KEY_USER_ID).commit();
        mPreference.edit().remove(KEY_USER_NAME).commit();
        mPreference.edit().remove(KEY_USER_PASSWORD).commit();
        mPreference.edit().remove(KEY_USER_ARRAY).commit();

        mPreference.edit().remove(KEY_USER_LOGINID).commit();
        mPreference.edit().remove(KEY_USER_COMPANY).commit();
        mPreference.edit().remove(KEY_IS_AUTOLOGIN).commit();
        mPreference.edit().remove(KEY_USER_Pricinctname).commit();
        mPreference.edit().remove(KEY_USER_NAVIGATION).commit();
    }

    /**
     * 清除用户权限
     */
    public void clearUserArray() {
        mPreference.edit().remove(KEY_USER_ARRAY).commit();
    }

    /**
     * 用户信息是否存在
     */
    public boolean hasUserInfo() {
        int id = mPreference.getInt(KEY_USER_ID, -1);
        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取用户信息
     */
    public User getUserInfo() {
        String id = mPreference.getString(KEY_USER_ID, "");
        if (id.equals("")) {
            return null;
        }
        User user = new User();
        user.setId(id);
        user.setUsername(mPreference.getString(KEY_USER_NAME, ""));
        user.setPwd(mPreference.getString(KEY_USER_PASSWORD, ""));
        user.setArray(mPreference.getString(KEY_USER_ARRAY, ""));
        user.setCompanyname(mPreference.getString(KEY_USER_COMPANY, ""));
        user.setPricinctname(mPreference.getString(KEY_USER_Pricinctname, ""));
        user.setLoginId(mPreference.getString(KEY_USER_LOGINID, ""));
        user.setAutoLogin(mPreference.getBoolean(KEY_IS_AUTOLOGIN, false));
        user.setNavigation(mPreference.getBoolean(KEY_USER_NAVIGATION, false));
        return user;
    }

    /**
     * 保存Banner数据信息
     * @param movies
     */
    public void saveBannerData(MovieSubject movies) {
        String moviesStr = movie2String(movies);
        mPreference.edit().putString(KEY_BANNER_DATA, moviesStr).commit();
    }

    /**
     * 保存Banner数据信息
     */
    public MovieSubject getBannerData() {
        String moviesStr = mPreference.getString(KEY_BANNER_DATA, "");
        MovieSubject movieSubject = string2MovieSubject(moviesStr);
        return movieSubject;
    }

    /**
     * list转为String
     * @param movies
     * @return
     */
    public static String movie2String(MovieSubject movies) {
        String movieListString = "";
        try {
            // 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // 然后将得到的字符数据装载到ObjectOutputStream
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    byteArrayOutputStream);
            // writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
            objectOutputStream.writeObject(movies);
            // 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
            movieListString = new String(Base64.encode(
                    byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            // 关闭objectOutputStream
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieListString;
    }

    /**
     * String转为list
     * @param movieListString
     * @return
     */
    public static MovieSubject string2MovieSubject(String movieListString) {
        MovieSubject movieData = new MovieSubject();
        try {
            byte[] mobileBytes = Base64.decode(movieListString.getBytes(),
                    Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    mobileBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    byteArrayInputStream);
            movieData = (MovieSubject) objectInputStream
                    .readObject();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return movieData;
    }

}
