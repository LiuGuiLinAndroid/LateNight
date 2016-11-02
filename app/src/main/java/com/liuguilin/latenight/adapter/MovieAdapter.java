package com.liuguilin.latenight.adapter;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.adapter
 *  文件名:   MovieAdapter
 *  创建者:   LGL
 *  创建时间:  2016/11/2 15:10
 *  描述：    电影适配器
 */

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.MovieData;
import com.liuguilin.latenight.util.GlideUtils;

import java.util.List;

public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<MovieData> mList;
    private MovieData data;
    private WindowManager wm;
    private int mScreenWidth;

    public MovieAdapter(Context mContext, List<MovieData> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        // 屏幕宽度
        mScreenWidth = wm.getDefaultDisplay().getWidth();
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
            convertView = inflater.inflate(R.layout.movie_item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        data = mList.get(position);
        if (!TextUtils.isEmpty(data.getImgUrl())) {
            GlideUtils.loadImageView(mContext,data.getImgUrl(),viewHolder.imageView
            );
        } else {
            viewHolder.imageView.setBackgroundResource(R.drawable.ic_development);
        }
        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
    }
}
