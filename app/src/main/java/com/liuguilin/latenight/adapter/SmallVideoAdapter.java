package com.liuguilin.latenight.adapter;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.adapter
 *  文件名:   SmallVideoAdapter
 *  创建者:   LGL
 *  创建时间:  2016/11/2 13:43
 *  描述：    小视频适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.SmallVideoData;
import com.liuguilin.latenight.util.PicassoUtils;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class SmallVideoAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private SmallVideoData data;
    private List<SmallVideoData> mList;

    private int widht, height;
    private WindowManager wm;

    public SmallVideoAdapter(Context mContext, List<SmallVideoData> mList) {
        this.mContext = mContext;
        this.mList = mList;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        widht = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
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
            convertView = inflater.inflate(R.layout.small_video_item, null);
            viewHolder.jc = (JCVideoPlayerStandard) convertView.findViewById(R.id.custom_videoplayer_standard);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        data = mList.get(position);
        viewHolder.jc.setUp(data.getUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, data.getTitle());
        //Picasso.with(mContext).load(data.getImgUrl()).into(viewHolder.jc.thumbImageView);
        PicassoUtils.loadImageViewSize(mContext,data.getImgUrl(),widht,height/3,viewHolder.jc.thumbImageView);
        //设置预览
        return convertView;
    }

    class ViewHolder {
        private JCVideoPlayerStandard jc;
    }
}
