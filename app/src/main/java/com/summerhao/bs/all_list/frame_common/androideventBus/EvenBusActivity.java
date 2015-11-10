package com.summerhao.bs.all_list.frame_common.androideventBus;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.summerhao.bs.R;
import com.summerhao.bs.all_list.ui_common.drawerlayout.StatusBarCompat;
import com.summerhao.bs.all_list.view_common.swipeback.BaseSwipeBackActivity;
import com.summerhao.bs.utils.IntentUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

/**
 * @author xiahao
 * @Description TODO
 * @date 2015/11/10
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class EvenBusActivity extends BaseSwipeBackActivity {

    private TextView tv_show;
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
        tv_show = (TextView) findViewById(R.id.tv_show);
        but_get = (Button) findViewById(R.id.get);
        but_get.setText("跳转界面");
    }


    @Override
    protected void init() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void widgetListener() {
        but_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.gotoActivity(EvenBusActivity.this,SencodeActivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    // 接收方法,默认的tag,执行在UI线程
    @Subscriber
    private void updateUser(User user) {
        Log.e("TAG", "### update user name = " + user.name);
        tv_show.setText(user.name);
    }

    // 含有my_tag,当用户post事件时,只有指定了"my_tag"的事件才会触发该函数,执行在UI线程
    @Subscriber(tag = "my_tag")
    private void updateUserWithTag(User user) {
        Log.e("TAG", "### update user with my_tag, name = " + user.name);
    }

    /// 含有my_tag,当用户post事件时,只有指定了"my_tag"的事件才会触发该函数,
    // post函数在哪个线程执行,该函数就执行在哪个线程
    @Subscriber(tag = "my_tag", mode= ThreadMode.POST)
    private void updateUserWithMode(User user) {
        Log.e("TAG", "### update user with my_tag, name = " + user.name);
    }

    // 含有my_tag,当用户post事件时,只有指定了"my_tag"的事件才会触发该函数,执行在一个独立的线程
    @Subscriber(tag = "my_tag", mode = ThreadMode.ASYNC)
    private void updateUserAsync(User user) {
        Log.e("TAG", "### update user async , name = " + user.name + ", thread name = " + Thread.currentThread().getName());
    }

}
