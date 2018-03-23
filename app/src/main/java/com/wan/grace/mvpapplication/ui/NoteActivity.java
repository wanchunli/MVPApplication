package com.wan.grace.mvpapplication.ui;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;
import com.wan.grace.mvpapplication.ui.presenter.NotePresenter;
import com.wan.grace.mvpapplication.ui.view.NoteView;

import butterknife.BindView;

public class NoteActivity extends MVPBaseActivity<NoteView,NotePresenter> implements NoteView{

    @BindView(R.id.edittext)
    EditText edittext;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_note;
    }

    @Override
    protected NotePresenter createPresenter() {
        return new NotePresenter(NoteActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void setDate(String dateStr) {

    }
}
