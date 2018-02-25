package com.wan.grace.mvpapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.utils.CommonViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */
public class FunctionAdpter extends BaseAdapter<String> {

    private boolean isShowDistance = false;

    public FunctionAdpter(Context context, List<String> list) {
        super(context, list);
    }

    public boolean isShowDistance() {
        return isShowDistance;
    }

    public void setShowDistance(boolean showDistance) {
        isShowDistance = showDistance;
    }

    public CommonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CommonViewHolder(getView(viewGroup, R.layout.item_function));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder commonViewHolder, final int position) {
        super.onBindViewHolder(commonViewHolder, position);
        commonViewHolder.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnItemClickListener() != null) {
                    getOnItemClickListener().onItemClickListener(v, position);
                }
            }
        });
    }

}
