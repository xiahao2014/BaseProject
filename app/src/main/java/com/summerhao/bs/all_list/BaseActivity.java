package com.summerhao.bs.all_list;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.summerhao.bs.MyApplication;
import com.summerhao.bs.R;
import com.summerhao.bs.utils.ToolAlert;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;


/**
 * @author xiahao
 * @Description TODO
 * @date 2015/10/30
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public abstract class BaseActivity extends AppCompatActivity {


    /**
     * 广播接收器
     */
    public BroadcastReceiver receiver;
    /**
     * 广播过滤器
     */
    public IntentFilter filter;

    /**
     * 当前Activity的弱引用，防止内存泄露
     **/
    private WeakReference<Activity> context = null;
    /***
     * 整个应用Applicaiton
     **/
    private MyApplication mApplication = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());


        //获取应用Application
        mApplication = (MyApplication) getApplicationContext();
        //将当前Activity压入栈
        context = new WeakReference<Activity>(this);
        mApplication.pushTask(context);

        ButterKnife.inject(this);
        initGetData();
        initToolBar();
        findViews();
        init();
        widgetListener();
        registerReceiver();

    }

    /**
     * 弹出等待对话框
     *
     * @param message 提示信息
     */
    public void showLoading(String message) {
        ToolAlert.loading(this, message);
    }

    /**
     * 弹出等待对话框
     *
     * @param message  提示信息
     * @param listener 按键监听器
     */
    public void showLoading(String message, ToolAlert.ILoadingOnKeyListener listener) {
        ToolAlert.loading(this, message, listener);
    }

    /**
     * 更新等待对话框显示文本
     *
     * @param message 需要更新的文本内容
     */
    public void updateLoadingText(String message) {
        ToolAlert.updateProgressText(message);
    }

    /**
     * 关闭等待对话框
     */
    public void closeLoading() {
        ToolAlert.closeLoading();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        mApplication.removeTask(context);
        super.onDestroy();
    }


    /**
     * 获取显示view的xml文件ID
     * <p>
     * 在Activity的 {@link #onCreate(Bundle)}里边被调用
     *
     * @return xml文件ID
     * @version 1.0
     * @createTime 2014年4月21日, 下午2:31:19
     * @updateTime 2014年4月21日, 下午2:31:19
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    protected abstract int getContentViewId();


    /**
     * 初始化标题栏toolbar
     * <p>
     * 在Activity的 {@link #getContentViewId()(Bundle)}里边被调用
     *
     * @return xml文件ID
     * @version 1.0
     * @createTime 2014年4月21日, 下午2:31:19
     * @updateTime 2014年4月21日, 下午2:31:19
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    protected abstract void initToolBar();


    /**
     * 控件查找
     * <p>
     * 在 {@link #getContentViewId()} 之后被调用
     *
     * @version 1.0
     * @createTime 2014年4月21日, 下午1:58:20
     * @updateTime 2014年4月21日, 下午1:58:20
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    protected abstract void findViews();

    /**
     * 初始化应用程序，设置一些初始化数据都获取数据等操作
     * <p>
     * 在{@link #widgetListener()}之后被调用
     *
     * @version 1.0
     * @createTime 2014年4月21日, 下午1:55:15
     * @updateTime 2014年4月21日, 下午1:55:15
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    protected abstract void init();

    /**
     * 获取上一个界面传送过来的数据
     * <p>
     * 在{@link BaseActivity#init()}之前被调用
     *
     * @version 1.0
     * @createTime 2014年4月21日, 下午2:42:56
     * @updateTime 2014年4月21日, 下午2:42:56
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    protected void initGetData() {
    }

    /**
     * 组件监听模块
     * <p>
     * 在{@link #findViews()}后被调用
     *
     * @version 1.0
     * @createTime 2014年4月21日, 下午1:56:06
     * @updateTime 2014年4月21日, 下午1:56:06
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    protected abstract void widgetListener();


    /**
     * 注册广播
     * <p>
     * <br/> Version: 1.0
     * <br/> CreateTime:  2014年5月22日,下午1:41:25
     * <br/> UpdateTime:  2014年5月22日,下午1:41:25
     * <br/> CreateAuthor:  CodeApe
     * <br/> UpdateAuthor:  CodeApe
     * <br/> UpdateInfo:  (此处输入修改内容,若无修改可不写.)
     */
    protected void registerReceiver() {
        setFilterActions();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                BaseActivity.this.onReceive(context, intent);
            }
        };
        registerReceiver(receiver, filter);

    }

    /**
     * 设置广播过滤器
     * <p>
     * <br/> Version: 1.0
     * <br/> CreateTime:  2014年5月22日,下午1:43:15
     * <br/> UpdateTime:  2014年5月22日,下午1:43:15
     * <br/> CreateAuthor:  CodeApe
     * <br/> UpdateAuthor:  CodeApe
     * <br/> UpdateInfo:  (此处输入修改内容,若无修改可不写.)
     */
    protected void setFilterActions() {
        filter = new IntentFilter();
        // filter.addAction(BroadcastFilters.ACTION_PAY_SUCCESS); // 支付成功广播

    }

    /**
     * 广播监听回调
     * <p>
     * <br/> Version: 1.0
     * <br/> CreateTime:  2014年5月22日,下午1:40:30
     * <br/> UpdateTime:  2014年5月22日,下午1:40:30
     * <br/> CreateAuthor:  CodeApe
     * <br/> UpdateAuthor:  CodeApe
     * <br/> UpdateInfo:  (此处输入修改内容,若无修改可不写.)
     *
     * @param context 上下文
     * @param intent  广播附带内容
     */
    protected void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals(BroadcastFilters.WARNING_OUT_OF_LOGIN)) {// 被迫下线
//            // DialogUtil.showOffLineDg(this, true);
//        } else if (intent.getAction().equals(BroadcastFilters.ACTION_CHANGE_LANGUAGE)) {// 修改语言
//            resetView();
//        }
    }


    /**
     * 界面开始动画 (此处输入方法执行任务.)
     *
     * @version 1.0
     * @createTime 2015年5月9日, 下午2:36:15
     * @updateTime 2015年5月9日, 下午2:36:15
     * @createAuthor Xiaohuan
     * @updateAuthor Xiaohuan
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    protected void startAnimation() {
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    /**
     * 界面回退动画 (此处输入方法执行任务.)
     *
     * @version 1.0
     * @createTime 2015年5月9日, 下午2:36:33
     * @updateTime 2015年5月9日, 下午2:36:33
     * @createAuthor Xiaohuan
     * @updateAuthor Xiaohuan
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    protected void endAnimation() {// 开始动画
        overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
    }


    @Override
    public void onBackPressed() {// 监听回退键
        super.onBackPressed();
        endAnimation();
    }


}
