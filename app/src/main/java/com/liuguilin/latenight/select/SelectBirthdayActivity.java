package com.liuguilin.latenight.select;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.select
 *  文件名:   SelectBirthdayActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/7 16:47
 *  描述：    选择生日
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.SharePreUtils;

public class SelectBirthdayActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView select_tv_birthday;
    private Button btn_next;
    private DatePicker mDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_birthday);

        initView();
    }

    //初始化View
    private void initView() {
        select_tv_birthday = (TextView) findViewById(R.id.select_tv_birthday);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        mDatePicker = (DatePicker) findViewById(R.id.mDatePicker);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                SharePreUtils.putString(this, Constants.SHARE_USER_BIRTHDAY,
                        mDatePicker.getYear() + "-" + mDatePicker.getMonth() + "-" + mDatePicker.getDayOfMonth());
                startActivity(new Intent(this, SelectOccupationActivity.class));
                finish();
                break;
        }
    }
}
