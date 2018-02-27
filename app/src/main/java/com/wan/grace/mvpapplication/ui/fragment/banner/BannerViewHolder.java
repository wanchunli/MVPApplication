package com.wan.grace.mvpapplication.ui.fragment.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.bean.Movie;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * Created by 开发部 on 2018/2/7.
 */
public class BannerViewHolder implements MZViewHolder<Integer> {
    private ImageView mImageView;
    @Override
    public View createView(Context context) {
        // 返回页面布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
        mImageView = (ImageView) view.findViewById(R.id.banner_image);
        return view;
    }

//    @Override
//    public void onBind(Context context, int position, Movie movie) {
//        // 数据绑定
//        Glide.with(context).load(movie.images.large)
//                .diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
//    }

    @Override
    public void onBind(Context context, int position, Integer data) {
        // 数据绑定
        mImageView.setImageResource(data);
    }
}
