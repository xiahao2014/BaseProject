package com.summerhao.bs.all_list.frame_common.androideventBus;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.summerhao.bs.R;
import com.summerhao.bs.all_list.ui_common.drawerlayout.StatusBarCompat;
import com.summerhao.bs.all_list.view_common.swipeback.BaseSwipeBackActivity;

import org.simple.eventbus.EventBus;

/**
 * @author xiahao
 * @Description TODO
 * @date 2015/11/10
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class SencodeActivity extends BaseSwipeBackActivity {

    private Button but_get;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_evenbus;
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
        tv_title.setText("AndroidEvenBus");
    }

    @Override
    protected void findViews() {
        but_get = (Button) findViewById(R.id.get);
        but_get.setText("post数据到上一个界面");
    }

    @Override
    protected void init() {

    }

    @Override
    protected void widgetListener() {
        but_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EventBus.getDefault().post(new User("android"));


                EventBus.getDefault().post(new User("mr.simple"), "my_tag");


                EventBus.getDefault().postSticky(new User("sticky"));


            }
        });
    }
}
