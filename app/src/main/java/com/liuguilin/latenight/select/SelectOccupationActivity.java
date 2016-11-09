package com.liuguilin.latenight.select;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.select
 *  文件名:   SelectOccupationActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/7 16:47
 *  描述：    选择职业
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.adapter.FlowTagAdapter;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.interfaces.OnTagSelectListener;
import com.liuguilin.latenight.util.SharePreUtils;
import com.liuguilin.latenight.view.FlowTagLayout;

import java.util.ArrayList;
import java.util.List;

public class SelectOccupationActivity extends AppCompatActivity implements View.OnClickListener {

    //单选
    private FlowTagLayout mSizeFlowTagLayout;
    private FlowTagAdapter<String> mSizeTagAdapter;
    private List<String> dataSource = new ArrayList<>();
    private int flag = 0;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_occupation);

        initView();
        initData();
    }

    //初始化数据
    private void initData() {
        dataSource.add("Android工程师");
        dataSource.add("JAVA工程师");
        dataSource.add("C/C++工程师");
        dataSource.add("IOS工程师");
        dataSource.add("Python工程师");
        dataSource.add("BSP工程师工程师");
        dataSource.add("PHP工程师");
        dataSource.add("C#工程师");
        dataSource.add("UI设计师");
        dataSource.add("产品经理");
        dataSource.add("研发助理");
        dataSource.add("CEO");
        dataSource.add("CTO");
        dataSource.add("财务");
        dataSource.add("会计");
        dataSource.add("司机");
        mSizeTagAdapter.onlyAddAll(dataSource);
    }

    //初始化view
    private void initView() {
        mSizeFlowTagLayout = (FlowTagLayout) findViewById(R.id.single_choose_flow_layout);
        mSizeTagAdapter = new FlowTagAdapter<>(this);
        //默认选择第一个
        mSizeTagAdapter.setSelected(0);
        //单选模式
        mSizeFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        //设置适配器
        mSizeFlowTagLayout.setAdapter(mSizeTagAdapter);
        //监听
        mSizeFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int positoin, List<Integer> selectedList) {
                flag = positoin;
            }
        });

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                SharePreUtils.putString(this, Constants.SHARE_USER_OCCUPATION, dataSource.get(flag));
                startActivity(new Intent(this, SelectSchoolActivity.class));
                finish();
                break;
        }
    }
}
