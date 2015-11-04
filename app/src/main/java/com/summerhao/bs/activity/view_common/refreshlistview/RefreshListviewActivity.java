package com.summerhao.bs.activity.view_common.refreshlistview;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.summerhao.bs.R;
import com.summerhao.bs.activity.BaseActivity;
import com.summerhao.bs.activity.ui_common.drawerlayout.StatusBarCompat;
import com.summerhao.bs.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



/**
 * 项目名称：BaseProject
 * 类描述：listview的下拉刷新，上拉加载实现
 * 创建人：xiahao
 * 创建时间：2015/10/24 14:25
 * 修改人：xiahao
 * 修改时间：2015/10/24 14:25
 * 修改备注：
 */
public class RefreshListviewActivity extends BaseActivity {

    private List<String> mListItems;

    private PullToRefreshListView mpPullToRefreshListView;
    private ListView listView_indent;

    private ArrayAdapter<String> mAdapter;

    private int count = 1;

    private String[] mStrings = {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9", "test10"

            , "test11", "test12", "test13", "test14", "test15", "test16", "test17", "test18", "test19", "test20",

    };


    @Override
    protected int getContentViewId() {
        return R.layout.activity_refreshlistview;
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
        tv_title.setText("ListView");
    }

    @Override
    protected void findViews() {
        mpPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.listView_indent);
        listView_indent = mpPullToRefreshListView.getRefreshableView();
        mpPullToRefreshListView.setPullLoadEnabled(true);
        setLastUpdateTime();
    }

    @Override
    protected void init() {
        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
        listView_indent.setAdapter(mAdapter);
    }


    @Override
    protected void widgetListener() {
        mpPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mListItems.clear();
                refreshData();
                mAdapter.notifyDataSetChanged();
                // 刷新完成
                mpPullToRefreshListView.onPullDownRefreshComplete();
                mpPullToRefreshListView.onPullUpRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                List<String> addNewData = addNewData();
                if (addNewData != null) {
                    mListItems.addAll(addNewData);
                    mAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(RefreshListviewActivity.this, "没有更多的数据了", Toast.LENGTH_SHORT).show();

                }

                mpPullToRefreshListView.onPullUpRefreshComplete();
                mpPullToRefreshListView.onPullDownRefreshComplete();
                setLastUpdateTime();

            }
        });
    }

    /**
     * 设置下拉刷新 显示的时间
     */
    private void setLastUpdateTime() {
        String text = CommonUtil.getStringDate();
        mpPullToRefreshListView.setLastUpdatedLabel(text);
    }


    /**
     * 刷新数据
     */
    private void refreshData() {
        // 模拟网络延迟
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mListItems.addAll(Arrays.asList(mStrings));
    }

    /**
     * 上拉加载的数据
     *
     * @return
     */
    private List<String> addNewData() {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < 6; i++) {
            if (count <= 50) {
                list.add("添加" + count);
                count++;
            } else {
                return null;
            }
        }
        return list;
    }
}
