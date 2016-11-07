package com.liuguilin.latenight.select;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.select
 *  文件名:   SelectAgeActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/21 16:27
 *  描述：    选择年龄
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lichfaker.scaleview.BaseScaleView;
import com.lichfaker.scaleview.HorizontalScaleScrollView;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.SharePreUtils;

public class SelectAgeActivity extends AppCompatActivity implements View.OnClickListener{

    //下一步
    private Button btn_next;
    private TextView select_tv_age;
    private HorizontalScaleScrollView horizontalScale;
    private ImageView age_logo;
    private int age;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_age);

        initView();
    }

    private void initView() {
        age_logo = (ImageView) findViewById(R.id.age_logo);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        select_tv_age = (TextView) findViewById(R.id.select_tv_age);

        String sex = SharePreUtils.getString(this,Constants.SHARE_USER_SEX,"男");
        if(sex.equals("男")){
            age_logo.setBackgroundResource(R.drawable.boy_off);
        }else {
            age_logo.setBackgroundResource(R.drawable.girl_off);
        }

        horizontalScale = (HorizontalScaleScrollView) findViewById(R.id.horizontalScale);
        horizontalScale.setOnScrollListener(new BaseScaleView.OnScrollListener() {
            @Override
            public void onScaleScroll(int scale) {
                select_tv_age.setText(scale + "岁");
                age = scale;
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                SharePreUtils.putInt(this, Constants.SHARE_USER_AGE,age);
                startActivity(new Intent(SelectAgeActivity.this,SelectHeightActivity.class));
                finish();
                break;
        }
    }
}
