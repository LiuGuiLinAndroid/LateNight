package com.liuguilin.latenight.select;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.select
 *  文件名:   SelectWeightActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/21 16:50
 *  描述：    选择体重
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.liuguilin.gankclient.R;

public class SelectWeightActivity extends AppCompatActivity implements View.OnClickListener {

    //下一步
    private Button btn_next;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_weight);

        initView();
    }

    private void initView() {
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                startActivity(new Intent(this,SelectConstellationActivity.class));
                break;
        }
    }
}
