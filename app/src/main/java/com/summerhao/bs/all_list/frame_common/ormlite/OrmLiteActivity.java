package com.summerhao.bs.all_list.frame_common.ormlite;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.summerhao.bs.R;
import com.summerhao.bs.all_list.ui_common.drawerlayout.StatusBarCompat;
import com.summerhao.bs.all_list.view_common.swipeback.BaseSwipeBackActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xiahao
 * @Description ORMLite的使用
 * @date 2015/10/27
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class OrmLiteActivity extends BaseSwipeBackActivity implements View.OnClickListener {


    private Button add, query, delete, update;
    private TextView tv_show;

    private int count = 1;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_ormlite;
    }

    @Override
    protected void initToolBar() {
        StatusBarCompat.compat(this);
        ImageButton iv_left = (ImageButton) findViewById(R.id.iv_left);
        iv_left.setBackground(getResources().getDrawable(R.drawable.back_selector));
        iv_left.setVisibility(View.VISIBLE);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("ormLite");
    }

    @Override
    protected void findViews() {
        add = (Button) findViewById(R.id.add);
        query = (Button) findViewById(R.id.query);
        delete = (Button) findViewById(R.id.delete);
        update = (Button) findViewById(R.id.update);
        tv_show = (TextView) findViewById(R.id.tv_show);
    }

    @Override
    protected void init() {

    }


    @Override
    protected void widgetListener() {
        add.setOnClickListener(this);
        query.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                addData();
                queryAll();
                break;
            case R.id.query:
                queryByFor();
                break;
            case R.id.delete:
                deleteData();
                break;
            case R.id.update:
                updateData();
                break;

        }
    }


    private void addData() {
        UserDao userDao;
        try {
            User u1;
            userDao = new UserDao(this);
            u1 = new User("name" + count++, "数据" + count);
            userDao.save(u1);
            u1 = new User("name" + count++, "数据" + count);
            userDao.save(u1);
            u1 = new User("name" + count++, "数据" + count);
            userDao.save(u1);
            u1 = new User("name" + count++, "数据" + count);
            userDao.save(u1);
            u1 = new User("name" + count++, "数据" + count);
            userDao.save(u1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 查询所有
     */
    private void queryAll() {
        try {
            UserDao userDao = new UserDao(this);
            List<User> users = userDao.queryAll();
            if (users != null && users.size() > 0) {
                tv_show.setText(users.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void queryByFor() {
        //查询具体的某个字段值
//        try {
//            UserDao userDao = new UserDao(this);
//            List<User> users = userDao.query("name", "name10");
//            if (users != null && users.size() > 0) {
//                tv_show.setText(users.toString());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //查询具体的几个字段值
//        try {
//            UserDao userDao = new UserDao(this);
//            List<User> users = userDao.query(new String[]{"name,desc"}, new String[]{"name3", "desc3"});
//            if (users != null && users.size() > 0) {
//                tv_show.setText(users.toString());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //查询某一区间的数据(分页查询)
        try {
            UserDao userDao = new UserDao(this);
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> lowMap = new HashMap<String, Object>();
            lowMap.put("id", 2);
            Map<String, Object> highMap = new HashMap<String, Object>();
            highMap.put("id", 10);
            ArrayList<User> cashFlows = (ArrayList<User>) userDao.query(map, lowMap, highMap);

            if (cashFlows != null && cashFlows.size() > 0) {
                tv_show.setText("查询的数据是id=3~9的数据:" + '\n' + cashFlows.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void deleteData() {
        try {
            UserDao userDao = new UserDao(this);
            User user = new User();
            user.setId(5);
            userDao.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateData() {

        try {
            UserDao userDao = new UserDao(this);
            User u1 = new User("zhy-android", "2B青年");
            u1.setId(3);
            userDao.update(u1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
