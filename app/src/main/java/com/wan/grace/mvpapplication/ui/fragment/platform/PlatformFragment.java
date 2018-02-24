package com.wan.grace.mvpapplication.ui.fragment.platform;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.MVPBaseFragment;
import com.wan.grace.mvpapplication.bean.Movie;
import com.wan.grace.mvpapplication.bean.MovieSubject;
import com.wan.grace.mvpapplication.ui.fragment.banner.BannerViewHolder;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 开发部 on 2018/2/7.
 */

public class PlatformFragment extends MVPBaseFragment<PlatFormView, PlatFormPresenter> implements PlatFormView {

    public static final String TAG = "MZModeBannerFragment";
    public static final int[] RES = new int[]{R.mipmap.image5, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4, R.mipmap.image6, R.mipmap.image7, R.mipmap.image8};
    public static final int[] BANNER = new int[]{R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4, R.mipmap.banner5};
    Unbinder unbinder;
    @BindView(R.id.banner)
    MZBannerView mMZBanner;

    @Override
    protected PlatFormPresenter createPresenter() {
        return new PlatFormPresenter(getActivity());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_platform;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View rootView) {
        mMZBanner.setIndicatorVisible(false);
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(getContext(), "click page:" + position, Toast.LENGTH_LONG).show();
            }
        });
        mMZBanner.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "----->addPageChangeLisnter:" + position + "positionOffset:" + positionOffset + "positionOffsetPixels:" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "addPageChangeLisnter:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < RES.length; i++) {
//            list.add(RES[i]);
//        }
//
//        List<Integer> bannerList = new ArrayList<>();
//        for (int i = 0; i < BANNER.length; i++) {
//            bannerList.add(BANNER[i]);
//        }
//        mMZBanner.setIndicatorVisible(true);
//        mMZBanner.setPages(bannerList, new MZHolderCreator<BannerViewHolder>() {
//            @Override
//            public BannerViewHolder createViewHolder() {
//                return new BannerViewHolder();
//            }
//        });
        mPresenter.getMovieBanner();
    }

    @Override
    public void setDate(String dateStr, MovieSubject movieSubject) {
        setBanner(movieSubject.subjects);
    }

    @Override
    public void setPresenter(PlatFormPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
//        mMZBanner.start();
    }

    private void setBanner(List<Movie> movies){
        mMZBanner.setPages(movies, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mMZBanner.start();
    }
}