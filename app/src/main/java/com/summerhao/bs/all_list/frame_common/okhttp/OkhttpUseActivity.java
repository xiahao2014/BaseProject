package com.summerhao.bs.all_list.frame_common.okhttp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.summerhao.bs.R;
import com.summerhao.bs.all_list.ui_common.drawerlayout.StatusBarCompat;
import com.summerhao.bs.all_list.view_common.swipeback.BaseSwipeBackActivity;
import com.summerhao.bs.adapter.CommonAdapter;
import com.summerhao.bs.adapter.ViewHolder;
import com.summerhao.bs.utils.OkHttpClientManager;

import java.util.List;



/**
 * 项目名称：BaseProject
 * 类描述：Okhttp的使用
 * 创建人：xiahao
 * 创建时间：2015/10/25 15:59
 * 修改人：xiahao
 * 修改时间：2015/10/25 15:59
 * 修改备注：
 */
public class OkhttpUseActivity extends BaseSwipeBackActivity {

    private ListView mListView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_okhttpuse;
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
        tv_title.setText("OKHttp");
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.userlist);
    }

    @Override
    protected void init() {
        OkHttpClientManager.getAsyn("http://7xnsa7.com1.z0.glb.clouddn.com/users.txt",
                new OkHttpClientManager.ResultCallback<List<Data>>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(List<Data> us) {
                        Log.e("TAG", us.size() + "");
                        //mTv.setText(us.get(1).toString());
                        //  mListView.setAdapter(new MyListViewAdapter(us));
                        mListView.setAdapter(new MyListViewAdapter(OkhttpUseActivity.this, us, R.layout.okhttp_item));
                    }
                });
    }

    @Override
    protected void widgetListener() {

    }

    public class MyListViewAdapter extends CommonAdapter<Data> {
        public MyListViewAdapter(Context context, List<Data> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, Data data) {
            holder.setImageURL(R.id.iv_icon, data.url);
            holder.setText(R.id.tv_id, data.id);
            holder.setText(R.id.tv_author, data.author);
            holder.setText(R.id.tv_content, data.content);
        }

//        private List<Data> listDatas;
//
//        public MyListViewAdapter(List<Data> datas) {
//            this.listDatas = datas;
//        }
//
//        @Override
//        public int getCount() {
//            return listDatas.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return listDatas.get(i);
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            ViewHolder mViewHolder;
//            if(null == convertView){
//                mViewHolder = new ViewHolder();
//                convertView = getLayoutInflater().inflate(R.layout.okhttp_item, null);
//                mViewHolder = new ViewHolder();
//                mViewHolder.iv_icon = (ImageView)convertView.findViewById(R.id.iv_icon);
//                mViewHolder.tv_id = (TextView)convertView.findViewById(R.id.tv_id);
//                mViewHolder.tv_author = (TextView)convertView.findViewById(R.id.tv_author);
//                mViewHolder.tv_content = (TextView)convertView.findViewById(R.id.tv_content);
//
//                convertView.setTag(mViewHolder);
//            }else{
//                mViewHolder = (ViewHolder)convertView.getTag();
//            }
//            //设置数据
////           Map<String,Object> rowData =  (Map)getItem(position);
//            Data data =  listDatas.get(position);
//
//            //异步加载图片防止错位方法一：com.android.volley.toolbox.ImageLoader
////			ImageLoader mImageLoader = MApplication.getImageLoader();
////			ImageListener mImageListener = mImageLoader.getImageListener(mViewHolder.iv_icon, R.drawable.default_icon, R.drawable.ic_launcher);
////			mImageLoader.get((String)rowData.get("imageUrl"), mImageListener);
//
//            //异步加载图片防止错位方法二：com.nostra13.universalimageloader.core.ImageLoader
//          // universalimageloader.displayImage((String)rowData.get("imageUrl"), mViewHolder.iv_icon, ToolImage.getFadeOptions(R.drawable.default_icon,R.drawable.ic_launcher,R.drawable.ic_launcher));
////            OkHttpClientManager.displayImage(mViewHolder.iv_icon,
////                    "http://images.csdn.net/20150817/1.jpg");
//
//            mViewHolder.tv_id.setText(data.id);
//            mViewHolder.tv_author.setText(data.author);
//            mViewHolder.tv_content.setText(data.content);
//
//            return convertView;
//        }
//
//        class ViewHolder{
//            ImageView iv_icon;
//            TextView tv_id;
//            TextView tv_author;
//            TextView tv_content;
//        }
    }
}
