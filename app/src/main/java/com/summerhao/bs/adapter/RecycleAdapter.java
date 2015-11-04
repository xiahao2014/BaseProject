package com.summerhao.bs.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.summerhao.bs.R;


/**
 * @author xiahao
 * @Description TODO
 * @date 2015/10/22
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private String[] list;

    public RecycleAdapter(String[] list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    @SuppressLint("InflateParams")
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);
        ViewHolder vh = new ViewHolder(view);

        vh.recycler_item_name = (TextView) view.findViewById(R.id.recycler_item_name);

        return vh;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        viewHolder.recycler_item_name.setText(list[i]);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView recycler_item_name;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}