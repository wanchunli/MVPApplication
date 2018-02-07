package com.wan.grace.mvpapplication.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.adapter.CustomViewPagerAdapter;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;
import com.wan.grace.mvpapplication.ui.ContactsActivity;
import com.wan.grace.mvpapplication.ui.fragment.message.MessageFragment;
import com.wan.grace.mvpapplication.ui.fragment.platform.PlatformFragment;
import com.wan.grace.mvpapplication.ui.fragment.tools.ToolsFragment;
import com.wan.grace.mvpapplication.ui.fragment.user.UserFragment;
import com.wan.grace.mvpapplication.widget.CustomViewPager;

import butterknife.BindView;

public class MainActivity extends MVPBaseActivity<MainView, MainPresenter> implements MainView, View.OnClickListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.contact_layout)
    RelativeLayout contactLayout;
    @BindView(R.id.more_layout)
    RelativeLayout moreLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_pager)
    CustomViewPager mainPager;
    private CustomViewPagerAdapter customViewPagerAdapter;
    @BindView(R.id.platform_layout)
    RelativeLayout platformLayout;
    @BindView(R.id.message_layout)
    RelativeLayout messageLayout;
    @BindView(R.id.message_image)
    ImageView messageImage;
    @BindView(R.id.tools_layout)
    RelativeLayout toolsLayout;
    @BindView(R.id.person_image)
    ImageView personImage;
    @BindView(R.id.message_num)
    TextView messageNum;
    @BindView(R.id.person_layout)
    RelativeLayout personLayout;
    @BindView(R.id.main_menu)
    LinearLayout mainMenu;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(MainActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        contactLayout.setOnClickListener(this);
        platformLayout.setOnClickListener(this);
        messageLayout.setOnClickListener(this);
        toolsLayout.setOnClickListener(this);
        personLayout.setOnClickListener(this);
        customViewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        customViewPagerAdapter.addFragment(new PlatformFragment());
        customViewPagerAdapter.addFragment(new MessageFragment());
        customViewPagerAdapter.addFragment(new ToolsFragment());
        customViewPagerAdapter.addFragment(new UserFragment());
        mainPager.setAdapter(customViewPagerAdapter);
        mainPager.setCurrentItem(customViewPagerAdapter.TAB_PLATFORM, false);
        mainPager.addOnPageChangeListener(this);
        switchItem(0);

    }

    @Override
    public void setDate(String dateStr) {

    }

    @Override
    public void setPresenter(MainPresenter presenter) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_layout:
                Intent it = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(it);
                break;
            case R.id.platform_layout:
                platformLayout.setSelected(true);
                messageLayout.setSelected(false);
                toolsLayout.setSelected(false);
                personLayout.setSelected(false);
                break;
            case R.id.message_layout:
                platformLayout.setSelected(false);
                messageLayout.setSelected(true);
                toolsLayout.setSelected(false);
                personLayout.setSelected(false);
                break;
            case R.id.tools_layout:
                platformLayout.setSelected(false);
                messageLayout.setSelected(false);
                toolsLayout.setSelected(true);
                personLayout.setSelected(false);
                break;
            case R.id.person_layout:
                platformLayout.setSelected(false);
                messageLayout.setSelected(false);
                toolsLayout.setSelected(false);
                personLayout.setSelected(true);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switchItem(position);
    }

    @Override
    public void onPageSelected(int position) {
        switchItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        switchItem(state);
    }

    /*
      底部选项切换
     */
    private void switchItem(int param) {
        switch (param) {
            case CustomViewPagerAdapter.TAB_PLATFORM:
                platformLayout.setSelected(true);
                messageLayout.setSelected(false);
                toolsLayout.setSelected(false);
                personLayout.setSelected(false);
                break;
            case CustomViewPagerAdapter.TAB_MESSAGE:
                platformLayout.setSelected(false);
                messageLayout.setSelected(true);
                toolsLayout.setSelected(false);
                personLayout.setSelected(false);
                break;
            case CustomViewPagerAdapter.TAB_TOOLS:
                platformLayout.setSelected(false);
                messageLayout.setSelected(false);
                toolsLayout.setSelected(true);
                personLayout.setSelected(false);
                break;
            case CustomViewPagerAdapter.TAB_USER:
                platformLayout.setSelected(false);
                messageLayout.setSelected(false);
                toolsLayout.setSelected(false);
                personLayout.setSelected(true);
                break;
        }
    }
}
