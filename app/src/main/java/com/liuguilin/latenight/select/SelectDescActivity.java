package com.liuguilin.latenight.select;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.select
 *  文件名:   SelectDescActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/7 16:47
 *  描述：    简介
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.MainActivity;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.SharePreUtils;

public class SelectDescActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_desc;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_desc);

        initView();
    }

    //初始化View
    private void initView() {
        et_desc = (EditText) findViewById(R.id.et_desc);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                String desc = et_desc.getText().toString().trim();
                if(!TextUtils.isEmpty(desc)){
                    SharePreUtils.putString(this, Constants.SHARE_USER_DESC,desc);
                }else {
                    SharePreUtils.putString(this, Constants.SHARE_USER_DESC,"这个人很懒，什么都没有留下！");
                }
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("first_update_user","update");
                startActivity(intent);
                finish();
                break;
        }
    }
}
