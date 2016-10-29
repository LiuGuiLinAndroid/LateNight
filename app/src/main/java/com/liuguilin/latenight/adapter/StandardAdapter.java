package com.liuguilin.latenight.adapter;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.adapter
 *  文件名:   StandardAdapter
 *  创建者:   LGL
 *  创建时间:  2016/10/28 14:23
 *  描述：    标准的Adapter
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.StandardData;

import java.util.List;

public class StandardAdapter extends BaseAdapter {

    private Context mContext;
    private List<StandardData> mList;
    private StandardData data;
    private LayoutInflater inflater;

    public StandardAdapter(Context mContext, List<StandardData> mList) {
        this.mContext = mContext;
        this.mList = mList;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.standar_list_item,null);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.tv_url = (TextView) convertView.findViewById(R.id.tv_url);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        data = mList.get(position);
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_time.setText(data.getTime());
        viewHolder.tv_url.setText(data.getUrl());
        return convertView;
    }

    class ViewHolder {
        private TextView tv_title;
        private TextView tv_time;
        private TextView tv_url;
    }

}
