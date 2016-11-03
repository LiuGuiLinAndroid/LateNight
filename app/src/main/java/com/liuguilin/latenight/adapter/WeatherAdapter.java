package com.liuguilin.latenight.adapter;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.adapter
 *  文件名:   WeatherAdapter
 *  创建者:   LGL
 *  创建时间:  2016/11/3 11:39
 *  描述：    天气适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.WeatherData;

import java.util.List;

public class WeatherAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<WeatherData> mList;
    private WeatherData data;
    private int[] mDrawable = {R.drawable.clothes, R.drawable.cold, R.drawable.conditioning, R.drawable.wash_car, R.drawable.sport, R.drawable.uv};

    public WeatherAdapter(Context mContext, List<WeatherData> mList) {
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
            convertView = inflater.inflate(R.layout.weather_item, null);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        data = mList.get(position);
        viewHolder.iv.setBackgroundResource(mDrawable[position]);
        viewHolder.tv.setText(data.getText());
        return convertView;
    }

    class ViewHolder {
        private ImageView iv;
        private TextView tv;
    }
}
