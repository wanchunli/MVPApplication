package com.wan.grace.mvpapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;

/**
 * Created by 开发部 on 2018/3/22.
 */

public class Editor  extends AppCompatEditText {

    public Editor(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public Editor(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    public void insertDrawable(Bitmap b, String imagepath) {
        final SpannableString s = new SpannableString(imagepath);
        //得到drawable对象，即所要插入的图片
        Drawable d = new BitmapDrawable(b);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        //用这个drawable对象代替字符串imagepath
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        //包括0但是不包括magepath.length()即：imagepath.length()。[0,magepath.length())。值得注意的是当我们复制这个图片的时候，实际是复制了imagepath这个字符串。
        s.setSpan(span, 0, imagepath.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        append(s);
    }

}
