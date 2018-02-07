package com.wan.grace.mvpapplication.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MainViewPager extends ViewPager {

	public MainViewPager(Context context) {
		super(context);
	}

	public MainViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// 禁止滑动
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return true;
	}

	// 允许内部滑动控件滑动
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	}
}
