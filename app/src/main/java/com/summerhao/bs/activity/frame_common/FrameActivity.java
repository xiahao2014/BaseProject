package com.summerhao.bs.activity.frame_common;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.summerhao.bs.R;
import com.summerhao.bs.activity.BaseActivity;
import com.summerhao.bs.activity.ui_common.drawerlayout.StatusBarCompat;
import com.summerhao.bs.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 项目名称：BaseProject
 * 类描述：常用第三方框架封装列表
 * 创建人：xiahao
 * 创建时间：2015/10/25 15:55
 * 修改人：xiahao
 * 修改时间：2015/10/25 15:55
 * 修改备注：
 */
public class FrameActivity extends BaseActivity {



    private ListView mListView;
    public final static String SAMPLE_CODE = "com.summerhao.android.FRAME_COMMON";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_all_list;
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
        tv_title.setText("三方框架");
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.all_list);
    }

    @Override
    protected void init() {
        //构造适配器
        DemoActivityAdapter mAdapter = new DemoActivityAdapter(this);
        mAdapter.addItem(getListData());
        mListView.setAdapter(mAdapter);
    }


    @Override
    protected void widgetListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Intent intent = (Intent) map.get("intent");
                startActivity(intent);
                //过场动画
                overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
            }
        });
    }

    /**
     * 初始化列表数据
     *
     * @return
     */
    protected List<Map<String, Object>> getListData() {
        List<Map<String, Object>> mListViewData = new ArrayList<Map<String, Object>>();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(SAMPLE_CODE);
        List<ResolveInfo> mActivityList = getPackageManager().queryIntentActivities(mainIntent, 0);
        for (int i = 0; i < mActivityList.size(); i++) {
            ResolveInfo info = mActivityList.get(i);
            String label = info.loadLabel(getPackageManager()) != null ? info.loadLabel(getPackageManager()).toString() : info.activityInfo.name;

            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("title", label);
            temp.put("intent", buildIntent(info.activityInfo.applicationInfo.packageName, info.activityInfo.name));
            mListViewData.add(temp);

        }

        return mListViewData;
    }

    /**
     * 构建每一个Item点击Intent
     *
     * @param packageName
     * @param componentName
     * @return
     */
    protected Intent buildIntent(String packageName, String componentName) {
        Intent result = new Intent();
        result.setClassName(packageName, componentName);
        return result;
    }

    /**
     * 列表适配器
     */
    protected class DemoActivityAdapter extends BaseAdapter {

        public DemoActivityAdapter(Activity mContext) {
            super(mContext);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Holder mHolder = null;
            if (null == convertView) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.all_list_item, null);
                mHolder = new Holder();
                mHolder.label = (TextView) convertView.findViewById(R.id.ui_item_name);
                convertView.setTag(mHolder);
            } else {
                mHolder = (Holder) convertView.getTag();
            }

            //设置数据
            mHolder.label.setText((String) ((Map<String, Object>) getItem(position)).get("title"));

            return convertView;
        }

        class Holder {
            TextView label;
        }
    }
}
