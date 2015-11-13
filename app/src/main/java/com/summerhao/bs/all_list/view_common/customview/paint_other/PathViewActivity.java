package com.summerhao.bs.all_list.view_common.customview.paint_other;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.summerhao.bs.R;
import com.summerhao.bs.all_list.ui_common.drawerlayout.StatusBarCompat;
import com.summerhao.bs.all_list.view_common.swipeback.BaseSwipeBackActivity;

/**
 * 封装一些关于自定View的知识
 * 1、FontMetrics字体测量 (FontView）
 * top,ascent(上坡度),descent(下坡度),bottom,leading(行间距)
 * Baseline基线
 * 2、字体换行 (StaticLayoutView )
 * Typeface typeface = Typeface.createFromAsset(getAssets(), "kt.ttf");
 * mTextView.setTypeface(typeface);(设置字体样式)
 * 3、遮罩过滤器 (MaskFilterView)
 * 图片阴影 (BlurMaskFilterView) 通过Bitmap的extractAlpha()方法从原图中分离出一个Alpha通道位图并在计算模糊滤镜的时候使用该位图生成模糊效果
 * setLayerType(LAYER_TYPE_SOFTWARE, null); 关闭硬件加速
 * 浮雕效果 (EmbossMaskFilter)
 * 4、PathEffect (PathEffectView)
 * 心电图 (ECGView)
 * 阴影图 (ShadowView)
 * @date 2015/11/12
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class PathViewActivity extends BaseSwipeBackActivity{



    @Override
    protected int getContentViewId() {
        return R.layout.activity_view_paint;
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
        tv_title.setText("自定义View/paint相关");
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void widgetListener() {

    }
}
