package com.liuguilin.latenight.adapter;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.adapter
 *  文件名:   PictureAdapter
 *  创建者:   LGL
 *  创建时间:  2016/11/11 14:00
 *  描述：    图片适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.PictureData;
import com.liuguilin.latenight.util.PicassoUtils;

import java.util.List;

public class PictureAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<PictureData> mList;
    private PictureData data;

    private onPClickListener onClickListener;
    private WindowManager windowManager;
    private int width, height;

    public PictureAdapter(Context mContext, List<PictureData> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = windowManager.getDefaultDisplay().getWidth();
        height = windowManager.getDefaultDisplay().getHeight();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.picture_item, null);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_content = (ImageView) convertView.findViewById(R.id.tv_content);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data = mList.get(position);
        viewHolder.tv_title.setText(data.getTitle());
        //viewHolder.tv_content.setText(data.getImg());
        PicassoUtils.loadImageViewSize(mContext, data.getImg(), width, height / 2, viewHolder.tv_content);

        final View finalConvertView = convertView;
        viewHolder.tv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onPClickListener(finalConvertView, position);
                }
            }
        });
        viewHolder.tv_time.setText(data.getTime());

        return convertView;
    }

    class ViewHolder {
        private TextView tv_title;
        private ImageView tv_content;
        private TextView tv_time;
    }

    public interface onPClickListener {
        public void onPClickListener(View view, int postion);
    }

    //实现接口
    public void setOnPClickListener(onPClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}
