package com.summerhao.bs.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.summerhao.bs.R;
import com.summerhao.bs.activity.frame_common.FrameActivity;
import com.summerhao.bs.activity.function_common.FunctionActivity;
import com.summerhao.bs.activity.ui_common.UIActivity;
import com.summerhao.bs.activity.ui_common.drawerlayout.StatusBarCompat;
import com.summerhao.bs.activity.view_common.ViewActivity;
import com.summerhao.bs.adapter.DividerItemDecoration;
import com.summerhao.bs.adapter.RecycleAdapter;
import com.summerhao.bs.adapter.RecyclerItemClickListener;
import com.summerhao.bs.utils.IntentUtil;
import com.summerhao.bs.utils.KJActivityStack;
import com.summerhao.bs.utils.ToastUtil;


/**
 * @author xiahao
 * @Description 程序入口，列出一些要封装的功能
 * @date 2015/10/22
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView = null;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolBar() {
        StatusBarCompat.compat(this);
        ImageButton iv_left = (ImageButton) findViewById(R.id.iv_left);
        iv_left.setBackground(getResources().getDrawable(R.drawable.back_selector));
        iv_left.setVisibility(View.INVISIBLE);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("主页");

    }

    @Override
    protected void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    }

    @Override
    protected void init() {
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecycleAdapter(new String[]{"MVC封装", "UI类封装", "控件类封装", "功能类封装", "网络请求及优化类的封装", "常用三方框架封装"}));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void widgetListener() {
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemLongClick(View view, int position) {

            }

            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        IntentUtil.gotoActivity(MainActivity.this, UIActivity.class);
                        break;
                    case 2:
                        IntentUtil.gotoActivity(MainActivity.this, ViewActivity.class);
                        break;
                    case 3:
                        IntentUtil.gotoActivity(MainActivity.this, FunctionActivity.class);
                        break;
                    case 4:
                        break;
                    case 5:
                        IntentUtil.gotoActivity(MainActivity.this, FrameActivity.class);
                        break;

                }

            }
        }));
    }

    long waitTime = 2000;
    long touchTime = 0;

    /**
     * 监听[返回]键事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 返回键
        if (KeyEvent.KEYCODE_BACK == keyCode) {

            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                // ToolToast.showShort("再按一次，退出程序");
                ToastUtil.showToast(MainActivity.this, "再按一次，退出程序");
                touchTime = currentTime;
            } else {
                //  ((MApplication) getApplicationContext()).removeAll();

                KJActivityStack.create().AppExit(MainActivity.this);
            }

            return true;
        }
        return false;
    }
}
