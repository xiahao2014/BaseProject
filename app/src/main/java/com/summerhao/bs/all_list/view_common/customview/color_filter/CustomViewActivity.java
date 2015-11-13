package com.summerhao.bs.all_list.view_common.customview.color_filter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.summerhao.bs.R;
import com.summerhao.bs.all_list.ui_common.drawerlayout.StatusBarCompat;
import com.summerhao.bs.all_list.view_common.swipeback.BaseSwipeBackActivity;

/**
 * setColorFilter(1、2、3)
 * 1、自定View的一些混合模式的知识 PorterDuffColorFilter
 * 2、色彩矩阵颜色过滤器 ColorMatrixColorFilter
 * 3、光照颜色过滤 LightingColorFilter
 * 4、setXfermode(5、6、7)
 * 5、AvoidXfermode,
 * 6、PixelXorXfermode
 * 7、PorterDuffXfermode(图形混合模式)
 * @author xiahao
 * @Description TODO
 * @date 2015/11/12
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class CustomViewActivity extends BaseSwipeBackActivity {


    @Override
    protected int getContentViewId() {
        return R.layout.activity_customview;
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
        tv_title.setText("自定义View/混合模式");
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
