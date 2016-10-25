package com.liuguilin.latenight.activity;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   GirlActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/25 13:33
 *  描述：    妹子
 */

import android.os.Bundle;
import android.widget.GridView;

import com.liuguilin.gankclient.R;

public class GirlActivity extends BaseActivity {

    //接口
    public static final String GANK_SEARCH_GIRL = "http://gank.io/api/search/query/listview/category/福利/count/50/page/1";
    private GridView girlGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);

        initView();
    }

    //初始化View
    private void initView() {
        girlGridView = (GridView) findViewById(R.id.girlGridView);

    }
}
