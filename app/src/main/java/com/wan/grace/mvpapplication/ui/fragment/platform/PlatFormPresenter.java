package com.wan.grace.mvpapplication.ui.fragment.platform;

import android.content.Context;

import com.wan.grace.mvpapplication.AppContext;
import com.wan.grace.mvpapplication.api.ApiFactory;
import com.wan.grace.mvpapplication.api.MainApi;
import com.wan.grace.mvpapplication.base.BasePresenter;
import com.wan.grace.mvpapplication.bean.MovieSubject;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 开发部 on 2018/2/7.
 */
public class PlatFormPresenter extends BasePresenter<PlatFormView> {
    private Context context;
    private PlatFormView platFormView;

    public PlatFormPresenter(Context context) {
        this.context = context;
        ac = (AppContext) context.getApplicationContext();
    }

    public void getMovieBanner() {
        platFormView = getView();
        if (platFormView != null) {
            if (ac.getBannerData().getCount() == 0) {
                getBannerData();
            } else {
                platFormView.setDate("", ac.getBannerData());
                getBannerData();
            }
        }
    }

    public void getBannerData() {
        mainApi.getTop250(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieSubject -> {
                    initBannerData(movieSubject, platFormView);
                }, this::loadError);
    }

    public void initBannerData(MovieSubject movieSubject, PlatFormView platFormView) {
        platFormView.setDate("", movieSubject);
        ac.saveBannerData(movieSubject);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
//        Toast.makeText(context, R.string.app_name, Toast.LENGTH_SHORT).show();
    }

    public List<String> getFunList() {
        List<String> list = new ArrayList<>();
        list.add("地图查看");
        list.add("地图查看");
        list.add("地图查看");
        list.add("地图查看");
        list.add("地图查看");
        list.add("地图查看");
        list.add("地图查看");
        list.add("地图查看");
        return list;
    }
}
