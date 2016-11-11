package com.liuguilin.latenight.adapter;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.adapter
 *  文件名:   JokesAdapter
 *  创建者:   LGL
 *  创建时间:  2016/11/11 13:37
 *  描述：    段子适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.JokesData;

import java.util.List;

public class JokesAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<JokesData> mList;
    private JokesData data;

    public JokesAdapter(Context mContext, List<JokesData> mList) {
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.jokes_item, null);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data = mList.get(position);
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_content.setText(data.getContent());
        viewHolder.tv_time.setText(data.getTime());

        return convertView;
    }

    class ViewHolder {
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_time;
    }
}
