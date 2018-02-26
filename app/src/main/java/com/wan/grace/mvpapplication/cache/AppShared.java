package com.wan.grace.mvpapplication.cache;
import android.content.Context;
import android.content.SharedPreferences;
import com.wan.grace.mvpapplication.bean.User;
/**
 * 共享存储类
 */
public class AppShared {

    public static final String SHARED_NAME = "ANSO";

    public static final String KEY_IS_FIRST_BOOT = "is_first_boot";

    public static final String KEY_IS_REMEMBER = "is_remember";
    public static final String KEY_IS_AUTOLOGIN = "is_autologin";

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
        mPreference.edit().putBoolean(KEY_IS_AUTOLOGIN,user.isAutoLogin()).commit();
        mPreference.edit().putBoolean(KEY_USER_NAVIGATION,user.isNavigation()).commit();
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
        user.setCompanyname(mPreference.getString(KEY_USER_COMPANY,""));
        user.setPricinctname(mPreference.getString(KEY_USER_Pricinctname,""));
        user.setLoginId(mPreference.getString(KEY_USER_LOGINID,""));
        user.setAutoLogin(mPreference.getBoolean(KEY_IS_AUTOLOGIN,false));
        user.setNavigation(mPreference.getBoolean(KEY_USER_NAVIGATION,false));
        return user;
    }

}
