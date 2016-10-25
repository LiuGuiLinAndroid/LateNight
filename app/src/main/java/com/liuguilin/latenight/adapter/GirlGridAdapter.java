package com.liuguilin.latenight.adapter;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.adapter
 *  文件名:   GirlGridAdapter
 *  创建者:   LGL
 *  创建时间:  2016/10/25 13:38
 *  描述：    妹子适配器
 */

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.GirlData;
import com.liuguilin.latenight.util.GlideUtils;

import java.util.List;

public class GirlGridAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<GirlData> mList;
    private GirlData data;

    public GirlGridAdapter(Context mContext, List<GirlData> mList) {
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
            convertView = inflater.inflate(R.layout.girl_grid_item, null);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data = mList.get(position);
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_time.setText(data.getTime());

        if (!TextUtils.isEmpty(data.getImgUrl())) {
            GlideUtils.loadImageViewSize(mContext, data.getImgUrl(), 500, 500, viewHolder.iv_img);
        } else {
            viewHolder.iv_img.setBackgroundResource(R.drawable.ic_development);
        }
        return convertView;
    }

    class ViewHolder {
        private TextView tv_title;
        private TextView tv_time;
        private ImageView iv_img;
    }
}
