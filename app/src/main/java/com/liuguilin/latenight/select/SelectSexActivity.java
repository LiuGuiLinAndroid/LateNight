package com.liuguilin.latenight.select;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.select
 *  文件名:   SelectSexActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/21 16:11
 *  描述：    选择性别
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.SharePreUtils;

public class SelectSexActivity extends AppCompatActivity implements View.OnClickListener {

    //下一步
    private Button btn_next;
    private TextView select_tv_sex;
    private ImageView iv_boy;
    private ImageView iv_girl;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_sex);

        initView();
    }

    private void initView() {
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        select_tv_sex = (TextView) findViewById(R.id.select_tv_sex);
        iv_boy = (ImageView) findViewById(R.id.iv_boy);
        iv_boy.setOnClickListener(this);
        iv_girl = (ImageView) findViewById(R.id.iv_girl);
        iv_girl.setOnClickListener(this);
        iv_boy.setSelected(true);
        iv_girl.setSelected(false);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                SharePreUtils.putString(this,Constants.SHARE_USER_SEX,select_tv_sex.getText().toString());
                startActivity(new Intent(SelectSexActivity.this, SelectAgeActivity.class));
                finish();
                break;
            case R.id.iv_boy:
                select_tv_sex.setText("男");
                iv_boy.setSelected(true);
                iv_girl.setSelected(false);
                break;
            case R.id.iv_girl:
                select_tv_sex.setText("女");
                iv_boy.setSelected(false);
                iv_girl.setSelected(true);
                break;
        }
    }
}
