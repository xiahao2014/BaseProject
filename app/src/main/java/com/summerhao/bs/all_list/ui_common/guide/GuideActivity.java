package com.summerhao.bs.all_list.ui_common.guide;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.summerhao.bs.R;
import com.summerhao.bs.all_list.BaseActivity;
import com.summerhao.bs.all_list.ui_common.drawerlayout.StatusBarCompat;


/**
 * 项目名称：BaseProject
 * 类描述：引导界面的实现
 * 创建人：xiahao
 * 创建时间：2015/10/25 12:02
 * 修改人：xiahao
 * 修改时间：2015/10/25 12:02
 * 修改备注：
 */
public class GuideActivity extends BaseActivity {

    private ImageIndicatorView imageIndicatorView;



    @Override
    protected int getContentViewId() {
        return R.layout.activity_guide;
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
        tv_title.setText("引导界面");
    }

    @Override
    protected void findViews() {
        imageIndicatorView = (ImageIndicatorView) findViewById(R.id.guide_indicate_view);
    }

    @Override
    protected void init() {
        final Integer[] resArray = new Integer[] { R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3 };
        imageIndicatorView.setupLayoutByDrawable(resArray);
        imageIndicatorView.setIndicateStyle(ImageIndicatorView.INDICATE_USERGUIDE_STYLE);
        imageIndicatorView.show();
    }


    @Override
    protected void widgetListener() {
        imageIndicatorView.setOnItemChangeListener(new ImageIndicatorView.OnItemChangeListener() {
            @Override
            public void onPosition(int position, int totalCount) {
                if (position == totalCount) {
                    finish();
                }
            }
        });
    }
}
