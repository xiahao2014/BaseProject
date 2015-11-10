package com.summerhao.bs;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.summerhao.bs.all_list.BaseActivity;
import com.summerhao.bs.all_list.MainActivity;

import java.util.Random;

/**
 * @author xiahao
 * @Description TODO
 * @date 2015/11/5
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class SplishActivity extends BaseActivity {

    /**
     * 三个切换的动画
     */
    private Animation mFadeIn;
    private Animation mFadeInScale;
    private Animation mFadeOut;
    ImageView mImageView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splish;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void init() {
        mImageView = (ImageView) findViewById(R.id.image);
        int index = new Random().nextInt(3);
        if (index == 1) {
            mImageView.setImageResource(R.mipmap.entrance1);
        } else if (index == 2) {
            mImageView.setImageResource(R.mipmap.entrance2);
        } else {
            mImageView.setImageResource(R.mipmap.entrance3);
        }

        initAnim();
        setListener();
    }

    private void initAnim() {
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in);
        mFadeIn.setDuration(500);
        mFadeInScale = AnimationUtils.loadAnimation(this,
                R.anim.welcome_fade_in_scale);
        mFadeInScale.setDuration(2000);
        mFadeOut = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_out);
        mFadeOut.setDuration(500);
        mImageView.startAnimation(mFadeIn);
    }

    /**
     * 监听事件
     */
    public void setListener() {
        /**
         * 动画切换原理:开始时是用第一个渐现动画,当第一个动画结束时开始第二个放大动画,当第二个动画结束时调用第三个渐隐动画,
         * 第三个动画结束时修改显示的内容并且重新调用第一个动画,从而达到循环效果
         */
        mFadeIn.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                mImageView.startAnimation(mFadeInScale);
            }
        });
        mFadeInScale.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
//                startActivity(MainActivity.class);
                Intent intent = new Intent(SplishActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                // mImageView.startAnimation(mFadeOut);
            }
        });
        mFadeOut.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                // startActivity(MainActivity.class);
            }
        });
    }

    @Override
    protected void widgetListener() {

    }
}
