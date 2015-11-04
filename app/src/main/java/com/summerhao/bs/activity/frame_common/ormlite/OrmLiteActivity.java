package com.summerhao.bs.activity.frame_common.ormlite;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.summerhao.bs.R;
import com.summerhao.bs.activity.BaseActivity;
import com.summerhao.bs.activity.ui_common.drawerlayout.StatusBarCompat;

import java.util.List;



/**
 * @author xiahao
 * @Description ORMLite的使用
 * @date 2015/10/27
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class OrmLiteActivity extends BaseActivity implements View.OnClickListener {


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
                queryData();

                break;
            case R.id.query:
                queryData();

                /*******通过name查找数据*****/
                UserDao userDao = new UserDao(this);
                List<User> name3 = userDao.queryByName("name3");
                Log.e("TAG", name3.size() + "");
                Log.e("TAG", name3.get(0).getId() + "");
                Log.e("TAG", name3.get(0).getName() + "");
                Log.e("TAG", name3.get(0).getDesc() + "");


                /*******通过id查找数据*****/
                User user = userDao.get(15);
                Log.e("TAG", user.getId() + "");
                Log.e("TAG", user.getName());
                Log.e("TAG", user.getDesc());

                break;
            case R.id.delete:

                userDao = new UserDao(this);
                /*******删除所有数据*****/
                // deleteData();
                /*******通过name删除数据*****/
                userDao.deleteDataByName("数据2");
                break;
            case R.id.update:
                //updateData();

                userDao = new UserDao(this);
                userDao.updateData(12);

                break;

        }
    }


    private void addData() {
        User u1;
        UserDao userDao = new UserDao(this);
        u1 = new User("name" + count++, "数据" + count);
        userDao.add(u1);
        u1 = new User("name" + count++, "数据" + count);
        userDao.add(u1);
        u1 = new User("name" + count++, "数据" + count);
        userDao.add(u1);
        u1 = new User("name" + count++, "数据" + count);
        userDao.add(u1);
        u1 = new User("name" + count++, "数据" + count);
        userDao.add(u1);
    }

    private void queryData() {
        UserDao userDao = new UserDao(this);
        List<User> users = userDao.queryData();
        if (users != null && users.size() > 0) {
            tv_show.setText(users.toString());
        }
    }

    private void deleteData() {
        UserDao userDao = new UserDao(this);
        userDao.deleteData();
    }

    private void updateData() {
//        UserDao userDao = new UserDao(this);
//        userDao.updateData();
    }


}
