package com.android.mvp2.ui.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mvp2.R;
import com.android.mvp2.data.model.DemoData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyListAdapter extends BaseAdapter {

    private List<DemoData> demoDatas;

    private LayoutInflater mInflater;

    public MyListAdapter(Context context, List<DemoData> demoDatas) {
        this.demoDatas = demoDatas;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return demoDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return demoDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_repo, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DemoData data = demoDatas.get(position);
        holder.name.setText(data.getName());
        holder.detail.setText(data.getDescription());

        return convertView;
    }

    public final class ViewHolder {

        @BindView(R.id.item_iv_repo_name)
        public TextView name;

        @BindView(R.id.item_iv_repo_detail)
        public TextView detail;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

}