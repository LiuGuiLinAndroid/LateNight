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

import com.liuguilin.latenight.entity.StandardData;

import java.util.List;

public class StandardAdapter extends BaseAdapter {

    private Context mContext;
    private List<StandardData> mList;
    private StandardData data;
    private LayoutInflater inflater;

    public StandardAdapter(Context mContext,List<StandardData> mList){
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
        return null;
    }

    class ViewHolder {

    }

}
