package com.wan.grace.mvpapplication.base;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.constants.Constants;

import butterknife.ButterKnife;

/**
 * Created by Werb on 2016/7/25.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * Base of Activity
 */
public abstract class MVPBaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity{

    protected T mPresenter;
    protected Toolbar mToolbar;
    private String TAG = "MVPBaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //允许为空，不是所有都要实现MVP模式
        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }
        setContentView(provideContentViewId());//布局
        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar); //把Toolbar当做ActionBar给设置
            if (canBack()) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null)
                    actionBar.setDisplayHomeAsUpEnabled(true);//设置ActionBar一个返回箭头，主界面没有，次级界面有
            }
        }
        initViews();
    }

    public void initViews(){

    }

    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp, boolean isShowRight) {
        initToolBar(toolbar, name, showHomeAsUp, isShowRight, 0);
    }

    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp, boolean isShowRight, int rightType) {
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 此时android.R.id.home即为返回箭头
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 判断当前 Activity 是否允许返回
     * 主界面不允许返回，次级界面允许返回
     *
     * @return false
     */
    public boolean canBack() {
        return false;
    }

    /**
     * 判断子Activity是否需要刷新功能
     *
     * @return false
     */
    public Boolean isSetRefresh() {
        return false;
    }

    public void showTips(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public int getCompatColor(int color) {
        return ContextCompat.getColor(this, color);
    }

    protected abstract T createPresenter();

    abstract protected int provideContentViewId();//用于引入布局文件

    /**
     * 检查权限方法
     * @param permissions
     * @return
     */
    public boolean hasPermissions(String... permissions) {
        for (String permission : permissions) {
            if(ContextCompat.checkSelfPermission(this,permission)!=
                    PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    /**
     * 权限请求方法
     * @param code
     * @param permissions
     */
    public void requestPersions(int code,String... permissions){
        ActivityCompat.requestPermissions(this,permissions,code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case Constants.WRITE_EXTERNAL_CODE:
                doWritePermission();
                break;
            case Constants.READ_EXTERNAL_CODE:
                doReadPermission();
                break;
            case Constants.READ_CONTACT_CODE:
                doReadContactPermission();
                break;
        }

    }

    /**
     * 默认写内存的权限
     */
    public void doWritePermission(){

    }

    /**
     * 默认读内存的权限
     */
    public void doReadPermission(){

    }

    /**
     * 默认读联系人的权限
     */
    public void doReadContactPermission(){

    }
}
