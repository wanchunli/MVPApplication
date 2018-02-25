package com.wan.grace.mvpapplication.ui.main;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.adapter.CustomViewPagerAdapter;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;
import com.wan.grace.mvpapplication.ui.CompassActivity;
import com.wan.grace.mvpapplication.ui.ContactsActivity;
import com.wan.grace.mvpapplication.ui.fragment.message.MessageFragment;
import com.wan.grace.mvpapplication.ui.fragment.platform.PlatformFragment;
import com.wan.grace.mvpapplication.ui.fragment.tools.ToolsFragment;
import com.wan.grace.mvpapplication.ui.fragment.user.UserFragment;
import com.wan.grace.mvpapplication.utils.AnimUtil;
import com.wan.grace.mvpapplication.widget.CustomViewPager;

import butterknife.BindView;

public class MainActivity extends MVPBaseActivity<MainView, MainPresenter> implements MainView,
        View.OnClickListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.contact_layout)
    RelativeLayout contactLayout;
    @BindView(R.id.more_layout)
    RelativeLayout moreLayout;
    @BindView(R.id.tools_title)
    TextView toolsTitle;
    @BindView(R.id.main_pager)
    CustomViewPager mainPager;
    CustomViewPagerAdapter customViewPagerAdapter;
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
//    @BindView(R.id.blurview)
//    RealtimeBlurView blurview;

    private PopupWindow mPopupWindow;
    private AnimUtil animUtil;
    private float bgAlpha = 1f;
    private boolean bright = false;
    private static final long DURATION = 200;
    private static final float START_ALPHA = 0.7f;
    private static final float END_ALPHA = 1f;

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
        //状态栏透明和间距处理
//        StatusBarUtil.immersive(this);
//        StatusBarUtil.setPaddingSmart(this, blurview);
//        StatusBarUtil.setPaddingSmart(this, mToolbar);
    }

    private void initView() {
        contactLayout.setOnClickListener(this);
        moreLayout.setOnClickListener(this);
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
        mainPager.setCurrentItem(CustomViewPagerAdapter.TAB_PLATFORM, false);
        mainPager.addOnPageChangeListener(this);
        mainPager.setOffscreenPageLimit(4);
        switchItem(0);

        //初始化popuwindow
        mPopupWindow = new PopupWindow(this);
        animUtil = new AnimUtil();

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
                Intent it_contact = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(it_contact);
                break;
            case R.id.more_layout:
                showPop();
                toggleBright();
                break;
            case R.id.platform_layout:
                mainPager.setCurrentItem(CustomViewPagerAdapter.TAB_PLATFORM, false);
                platformLayout.setSelected(true);
                messageLayout.setSelected(false);
                toolsLayout.setSelected(false);
                personLayout.setSelected(false);
                toolsTitle.setText(getString(R.string.platform));
                contactLayout.setVisibility(View.GONE);
                break;
            case R.id.message_layout:
                mainPager.setCurrentItem(CustomViewPagerAdapter.TAB_MESSAGE, false);
                platformLayout.setSelected(false);
                messageLayout.setSelected(true);
                toolsLayout.setSelected(false);
                personLayout.setSelected(false);
                toolsTitle.setText(getString(R.string.message));
                contactLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.tools_layout:
                mainPager.setCurrentItem(CustomViewPagerAdapter.TAB_TOOLS, false);
                platformLayout.setSelected(false);
                messageLayout.setSelected(false);
                toolsLayout.setSelected(true);
                personLayout.setSelected(false);
                toolsTitle.setText(getString(R.string.tools));
                contactLayout.setVisibility(View.GONE);
                break;
            case R.id.person_layout:
                mainPager.setCurrentItem(CustomViewPagerAdapter.TAB_USER, false);
                platformLayout.setSelected(false);
                messageLayout.setSelected(false);
                toolsLayout.setSelected(false);
                personLayout.setSelected(true);
                toolsTitle.setText(getString(R.string.me));
                contactLayout.setVisibility(View.GONE);
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
                toolsTitle.setText(getString(R.string.platform));
                contactLayout.setVisibility(View.GONE);
                break;
            case CustomViewPagerAdapter.TAB_MESSAGE:
                platformLayout.setSelected(false);
                messageLayout.setSelected(true);
                toolsLayout.setSelected(false);
                personLayout.setSelected(false);
                toolsTitle.setText(getString(R.string.message));
                contactLayout.setVisibility(View.VISIBLE);
                break;
            case CustomViewPagerAdapter.TAB_TOOLS:
                platformLayout.setSelected(false);
                messageLayout.setSelected(false);
                toolsLayout.setSelected(true);
                personLayout.setSelected(false);
                toolsTitle.setText(getString(R.string.tools));
                contactLayout.setVisibility(View.GONE);
                break;
            case CustomViewPagerAdapter.TAB_USER:
                platformLayout.setSelected(false);
                messageLayout.setSelected(false);
                toolsLayout.setSelected(false);
                personLayout.setSelected(true);
                toolsTitle.setText(getString(R.string.me));
                contactLayout.setVisibility(View.GONE);
                break;
        }
    }

    private void showPop() {
        // 设置布局文件
        mPopupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.popu_add, null));
        // 设置pop透明效果
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        // 设置pop出入动画
        mPopupWindow.setAnimationStyle(R.style.popu_add);
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        mPopupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        mPopupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        mPopupWindow.setOutsideTouchable(true);
        // 相对于 + 号正下面，同时可以设置偏移量
        mPopupWindow.showAsDropDown(moreLayout, -50, -20);
        // 设置pop关闭监听，用于改变背景透明度
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toggleBright();
            }
        });
        TextView compassTextview = mPopupWindow.getContentView().findViewById(R.id.tv_1);
        compassTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CompassActivity.class);
                startActivity(it);
                mPopupWindow.dismiss();
            }
        });
        TextView tv2 = mPopupWindow.getContentView().findViewById(R.id.tv_2);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CompassActivity.class);
                startActivity(it);
                mPopupWindow.dismiss();
            }
        });
        TextView tv3 = mPopupWindow.getContentView().findViewById(R.id.tv_3);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CompassActivity.class);
                startActivity(it);
                mPopupWindow.dismiss();
            }
        });
        TextView tv4 = mPopupWindow.getContentView().findViewById(R.id.tv_4);
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CompassActivity.class);
                startActivity(it);
                mPopupWindow.dismiss();
            }
        });
        TextView tv5 = mPopupWindow.getContentView().findViewById(R.id.tv_5);
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CompassActivity.class);
                startActivity(it);
                mPopupWindow.dismiss();
            }
        });
    }

    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        animUtil.addUpdateListener(new AnimUtil.UpdateListener() {
            @Override
            public void progress(float progress) {
                // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);
            }
        });
        animUtil.addEndListner(new AnimUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
            }
        });
        animUtil.startAnimator();
    }

    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
