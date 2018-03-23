package com.wan.grace.mvpapplication.ui.presenter;

import android.content.Context;

import com.wan.grace.mvpapplication.base.BasePresenter;
import com.wan.grace.mvpapplication.ui.view.AttendView;
import com.wan.grace.mvpapplication.ui.view.NoteView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 开发部 on 2018/2/8.
 */

public class NotePresenter extends BasePresenter<NoteView> {

    private Context context;
    private NoteView noteView;
    private List<Map<String, String>> mp = new ArrayList<>();

    public NotePresenter(Context context) {
        this.context = context;
    }

}
