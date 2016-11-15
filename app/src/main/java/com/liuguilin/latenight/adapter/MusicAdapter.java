package com.liuguilin.latenight.adapter;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.adapter
 *  文件名:   MusicAdapter
 *  创建者:   LGL
 *  创建时间:  2016/11/10 14:51
 *  描述：    歌曲列表
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.MusicData;
import com.liuguilin.latenight.service.MusicService;
import com.liuguilin.latenight.util.GlideUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MusicAdapter extends BaseAdapter {

    private Context mContext;
    private List<MusicData> mList;
    private LayoutInflater inflater;
    private MusicData data;

    public MusicAdapter(Context mContext, List<MusicData> mList) {
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
            convertView = inflater.inflate(R.layout.music_item, null);
            viewHolder.ll_bg = (ImageView) convertView.findViewById(R.id.ll_bg);
            viewHolder.profile_image = (CircleImageView) convertView.findViewById(R.id.profile_image);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            //播放
            viewHolder.iv_play = (ImageView) convertView.findViewById(R.id.iv_play);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_message = (TextView) convertView.findViewById(R.id.tv_message);
            viewHolder.story_title = (TextView) convertView.findViewById(R.id.story_title);
            viewHolder.tv_context = (TextView) convertView.findViewById(R.id.tv_context);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data = mList.get(position);
        GlideUtils.loadImageView(mContext, data.getImgBgUrl(), viewHolder.ll_bg);
        GlideUtils.loadImageView(mContext, data.getImgPhotoUrl(), viewHolder.profile_image);
        viewHolder.tv_name.setText(data.getName());
        viewHolder.tv_time.setText(data.getTime());
        viewHolder.tv_title.setText(data.getTvTitle());
        viewHolder.tv_message.setText(data.getTvMessage());
        viewHolder.story_title.setText(data.getStory_title());
        viewHolder.tv_context.setText(data.getTvContent());

        //设置点击事件
        viewHolder.iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MusicService.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", data.getMusicUrl());
                intent.putExtras(bundle);
                mContext.startService(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        private ImageView ll_bg;
        private CircleImageView profile_image;
        private TextView tv_name;
        private TextView tv_time;

        //播放
        private ImageView iv_play;

        private TextView tv_title;
        private TextView tv_message;
        private TextView story_title;
        private TextView tv_context;
        //查看封面
        private TextView look_canvas;
    }
}
