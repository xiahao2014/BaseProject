package com.summerhao.bs.all_list.view_common.flowlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.summerhao.bs.R;
import com.summerhao.bs.all_list.ui_common.drawerlayout.StatusBarCompat;

import java.util.ArrayList;

/**
 * 流式标签实现
 *
 * @author xiahao
 * @Description TODO
 * @date 2015/11/11
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class FlowLayoutActivity extends Activity {

    ArrayList<String> names = new ArrayList<String>();
    FlowGroupView fl_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setData();
        View view = LayoutInflater.from(this).inflate(R.layout.activity_flowlayout, null);
        fl_view = (FlowGroupView) view.findViewById(R.id.fl_view);
        for (int i = 0; i < names.size(); i++) {
            addTextView(names.get(i));
        }
        setContentView(view);

        initToolbars();
    }

    private void initToolbars() {
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
        tv_title.setText("流式标签");
    }

    /**
     * 动态添加布局
     *
     * @param str
     */
    private void addTextView(String str) {
        TextView child = new TextView(this);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        params.setMargins(15, 15, 15, 15);

        child.setLayoutParams(params);
        child.setBackgroundResource(R.drawable.shape_textback);
        child.setText(str);
        child.setTextColor(Color.WHITE);
        initEvents(child);
        fl_view.addView(child);
        // 务必要加这句
        fl_view.requestLayout();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            // 动态添加 view
            addTextView(names.get((int) (Math.random() * names.size())));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 为每个view 添加点击事件
     *
     * @param tv
     */
    private void initEvents(final TextView tv) {
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(FlowLayoutActivity.this, tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        names.add("降龙十八掌");
        names.add("黯然销魂掌");
        names.add("左右互搏术");
        names.add("七十二路空明拳");
        names.add("小无相功");
        names.add("拈花指");
        names.add("打狗棍法");
        names.add("蛤蟆功");
        names.add("九阴白骨爪");
        names.add("一招半式闯江湖");
        names.add("醉拳");
        names.add("龙蛇虎豹");
        names.add("葵花宝典");
        names.add("吸星大法");
        names.add("如来神掌警示牌");
    }

}
