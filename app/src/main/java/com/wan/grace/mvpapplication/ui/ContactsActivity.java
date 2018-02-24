package com.wan.grace.mvpapplication.ui;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.adapter.ContactAdapter;
import com.wan.grace.mvpapplication.base.MVPBaseActivity;
import com.wan.grace.mvpapplication.bean.ContactBean;
import com.wan.grace.mvpapplication.constants.Constants;
import com.wan.grace.mvpapplication.ui.presenter.ContactPresenter;
import com.wan.grace.mvpapplication.ui.view.ContactView;
import com.wan.grace.mvpapplication.utils.CommonUtil;
import com.wan.grace.mvpapplication.utils.contacts_recycle.CustomItemDecoration;
import com.wan.grace.mvpapplication.utils.contacts_recycle.SideBar;
import com.wan.grace.mvpapplication.utils.contacts_recycle.itemAnimator.SlideInOutLeftItemAnimator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactsActivity extends MVPBaseActivity<ContactView,ContactPresenter> implements ContactView {

    private ContentResolver cr;
    private RecyclerView rl_recycle_view;
    private ContactAdapter mAdapter;
    private CustomItemDecoration decoration;
    private SideBar side_bar;
    private List<ContactBean> nameList = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_contact;
    }

    @Override
    protected ContactPresenter createPresenter() {
        return new ContactPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvents();
    }

    @Override
    public void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar, "通讯录", true, true);
        mAdapter = new ContactAdapter(this);
        rl_recycle_view = (RecyclerView) findViewById(R.id.rl_recycle_view);
        //侧边导航栏
        side_bar = (SideBar) findViewById(R.id.side_bar);
        layoutManager = new LinearLayoutManager(this);
        rl_recycle_view.setLayoutManager(layoutManager);
        rl_recycle_view.addItemDecoration(decoration = new CustomItemDecoration(this));
        rl_recycle_view.setItemAnimator(new SlideInOutLeftItemAnimator(rl_recycle_view));
        rl_recycle_view.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(ContactsActivity.this,nameList.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });

        //拿到内容访问者
        cr = getContentResolver();
        if (hasPermissions(Manifest.permission.READ_CONTACTS)) {
            mPresenter.getContacts();
//            getContacts();
        } else {
            settingPermission(Constants.READ_CONTACT_CODE,Manifest.permission.READ_CONTACTS);
//            requestPermissions(Constants.READ_CONTACT_CODE, Manifest.permission.READ_CONTACTS);
        }
    }

    @Override
    public void doReadContactPermission() {
        mPresenter.getContacts();
//        getContacts();
    }

    @Override
    public void setDate(String dateStr) {

    }

    @Override
    public void setList(String tagsStr,List<ContactBean> list) {
        nameList.clear();
        nameList.addAll(list);
//        side_bar.setIndexStr(tagsStr);
        decoration.setDatas(nameList, tagsStr);
        mAdapter.addAll(nameList);
    }

    public void initEvents() {
        side_bar.setIndexChangeListener(new SideBar.indexChangeListener() {
            @Override
            public void indexChanged(String tag) {
                if (TextUtils.isEmpty(tag) || nameList.size() <= 0) return;
                for (int i = 0; i < nameList.size(); i++) {
                    if (tag.equals(nameList.get(i).getIndexTag())) {
                        layoutManager.scrollToPositionWithOffset(i, 0);
//                        layoutManager.scrollToPosition(i);
                        return;
                    }
                }
            }
        });
    }

    private void initDatas() {
        String[] names = {"孙尚香", "安其拉", "白起", "不知火舞", "@小马快跑", "_德玛西亚之力_", "妲己", "狄仁杰", "典韦", "韩信",
                "老夫子", "刘邦", "刘禅", "鲁班七号", "墨子", "孙膑", "孙尚香", "孙悟空", "项羽", "亚瑟",
                "周瑜", "庄周", "蔡文姬", "甄姬", "廉颇", "程咬金", "后羿", "扁鹊", "钟无艳", "小乔", "王昭君", "虞姬",
                "李元芳", "张飞", "刘备", "牛魔", "张良", "兰陵王", "露娜", "貂蝉", "达摩", "曹操", "芈月", "荆轲", "高渐离",
                "钟馗", "花木兰", "关羽", "李白", "宫本武藏", "吕布", "嬴政", "娜可露露", "武则天", "赵云", "姜子牙",};
        for (String name : names) {
            ContactBean bean = new ContactBean();
            bean.setName(name);
            nameList.add(bean);
        }
        //对数据源进行排序
        CommonUtil.sortData(nameList);
        //返回一个包含所有Tag字母在内的字符串并赋值给tagsStr
        String tagsStr = CommonUtil.getTags(nameList);
        side_bar.setIndexStr(tagsStr);
        decoration.setDatas(nameList, tagsStr);
        mAdapter.addAll(nameList);
    }

    //添加联系人
    public void add(ContactBean bean) {
        nameList.add(bean);
        //对数据源进行排序
        CommonUtil.sortData(nameList);
        //返回一个包含所有Tag字母在内的字符串并赋值给tagsStr
        String tagsStr = CommonUtil.getTags(nameList);
        side_bar.setIndexStr(tagsStr);
        decoration.setDatas(nameList, tagsStr);
        //这里写死位置1 只是为了看动画效果
        mAdapter.add(bean, 0);
    }

//    查询本机联系人
    public void getContacts() {
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
//            mp.add(maps);//将map加入list集合中
        }

        //对数据源进行排序
        CommonUtil.sortData(nameList);
        //返回一个包含所有Tag字母在内的字符串并赋值给tagsStr
        String tagsStr = CommonUtil.getTags(nameList);
        side_bar.setIndexStr(tagsStr);
        decoration.setDatas(nameList, tagsStr);
        mAdapter.addAll(nameList);
//        mAdapter.addAll(nameList);
        mAdapter.notifyDataSetChanged();
    }

}
