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

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.GirlData;
import com.liuguilin.latenight.util.PicassoUtils;

import java.util.List;

public class GirlGridAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<GirlData> mList;
    private GirlData data;
    private int w, h;

    public GirlGridAdapter(Context mContext, List<GirlData> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        w = mContext.getResources().getDisplayMetrics().widthPixels;
        h = mContext.getResources().getDisplayMetrics().heightPixels;
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
            viewHolder.iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        data = mList.get(position);
        if (!TextUtils.isEmpty(data.getImgUrl())) {
            //GlideUtils.loadImageViewSize(mContext, data.getImgUrl(), w / 2, h / 4 - 20, viewHolder.iv_img);
            PicassoUtils.loadImageViewSize(mContext, data.getImgUrl(), w/2, h/4-20, viewHolder.iv_img);
        } else {
            viewHolder.iv_img.setBackgroundResource(R.drawable.ic_development);
        }
        return convertView;
    }

    class ViewHolder {
        private ImageView iv_img;
    }
}
