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
import android.widget.ImageView;
import android.widget.TextView;

import com.lichfaker.scaleview.BaseScaleView;
import com.lichfaker.scaleview.VerticalScaleScrollView;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.SharePreUtils;

public class SelectHeightActivity extends AppCompatActivity implements View.OnClickListener {

    //下一步
    private Button btn_next;
    private VerticalScaleScrollView horizontalScale;
    private TextView select_tv_height;
    private ImageView height_logo;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_height);

        initView();
    }

    private void initView() {
        height_logo = (ImageView) findViewById(R.id.height_logo);
        select_tv_height = (TextView) findViewById(R.id.select_tv_height);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        boolean sex = SharePreUtils.getBoolean(this,Constants.SHARE_USER_SEX,true);
        if(sex){
            height_logo.setBackgroundResource(R.drawable.boy_off);
        }else {
            height_logo.setBackgroundResource(R.drawable.girl_off);
        }


        horizontalScale = (VerticalScaleScrollView) findViewById(R.id.horizontalScale);
        horizontalScale.setOnScrollListener(new BaseScaleView.OnScrollListener() {
            @Override
            public void onScaleScroll(int scale) {
                select_tv_height.setText(scale + "CM");
                height = scale;
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
                SharePreUtils.putString(this, Constants.SHARE_USER_HEIGHT,height + "CM");
                startActivity(new Intent(SelectHeightActivity.this, SelectWeightActivity.class));
                finish();
                break;
        }
    }
}
