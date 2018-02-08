package com.wan.grace.mvpapplication.ui.presenter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.wan.grace.mvpapplication.base.BasePresenter;
import com.wan.grace.mvpapplication.bean.ContactBean;
import com.wan.grace.mvpapplication.ui.view.ContactView;
import com.wan.grace.mvpapplication.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogRecord;

/**
 * Created by 开发部 on 2018/2/8.
 */

public class ContactPresenter extends BasePresenter<ContactView> {

    private ContentResolver cr;
    private Context context;
    private ContactView contactView;
    private List<Map<String, String>> mp = new ArrayList<>();

    public ContactPresenter(Context context) {
        this.context = context;
        //拿到内容访问者
        cr = context.getContentResolver();
    }


    //查询本机联系人
    public void getContacts() {

        final Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                contactView = getView();
                List<ContactBean> list = (List<ContactBean>)msg.obj;
                //对数据源进行排序
                CommonUtil.sortData(list);
                //返回一个包含所有Tag字母在内的字符串并赋值给tagsStr
                String tagsStr = CommonUtil.getTags(list);
                contactView.setList(tagsStr,list);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ContactBean> list = getContactImp();
                Message msg = new Message();
                msg.obj = list;
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    private List<ContactBean> getContactImp() {
        List<ContactBean> nameList = new ArrayList<>();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cs = cr.query(uri, null, null, null, null, null);
        while (cs.moveToNext()) {
            //拿到联系人id 跟name
            int id = cs.getInt(cs.getColumnIndex("_id"));
            String name = cs.getString(cs.getColumnIndex("display_name"));
            //得到这个id的所有数据（data表）
            Uri uri1 = Uri.parse("content://com.android.contacts/raw_contacts/" + id + "/data");
            Cursor cs2 = cr.query(uri1, null, null, null, null, null);
            Map<String, String> maps = new HashMap<>();//实例化一个map
            while (cs2.moveToNext()) {
                //得到data这一列 ，包括很多字段
                String data1 = cs2.getString(cs2.getColumnIndex("data1"));
                //得到data中的类型
                String type = cs2.getString(cs2.getColumnIndex("mimetype"));
                String str = type.substring(type.indexOf("/") + 1, type.length());//截取得到最后的类型
                if ("name".equals(str)) {//匹配是否为联系人名字
                    maps.put("name", data1);
                    ContactBean bean = new ContactBean();
                    bean.setName(data1);
                    nameList.add(bean);
                }
                if ("phone_v2".equals(str)) {//匹配是否为电话
                    maps.put("phone", data1);
                }
                Log.i("test", data1 + "       " + type);
            }
            mp.add(maps);//将map加入list集合中
        }

        return nameList;
    }

}
