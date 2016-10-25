package com.liuguilin.latenight.select;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.select
 *  文件名:   SelectHeightActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/21 16:37
 *  描述：    选择身高
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lichfaker.scaleview.BaseScaleView;
import com.lichfaker.scaleview.HorizontalScaleScrollView;
import com.liuguilin.gankclient.R;

public class SelectHeightActivity extends AppCompatActivity implements View.OnClickListener {

    //下一步
    private Button btn_next;
    private HorizontalScaleScrollView horizontalScale;
    private TextView select_tv_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_height);

        initView();
    }

    private void initView() {
        select_tv_height = (TextView) findViewById(R.id.select_tv_height);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        horizontalScale = (HorizontalScaleScrollView) findViewById(R.id.horizontalScale);
        horizontalScale.setOnScrollListener(new BaseScaleView.OnScrollListener() {
            @Override
            public void onScaleScroll(int scale) {
                select_tv_height.setText(scale + "CM");
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                startActivity(new Intent(this, SelectWeightActivity.class));
                break;
        }
    }
}
