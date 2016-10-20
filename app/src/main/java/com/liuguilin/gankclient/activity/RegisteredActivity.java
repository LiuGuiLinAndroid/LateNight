package com.liuguilin.gankclient.activity;

/*
 *  项目名：  GankClient 
 *  包名：    com.liuguilin.gankclient.activity
 *  文件名:   RegisteredActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/18 16:44
 *  描述：    注册
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.liuguilin.gankclient.R;
import com.liuguilin.gankclient.entity.Constants;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class RegisteredActivity extends BaseActivity implements View.OnClickListener{

    //电话号码
    private EditText reg_et_phone;
    //验证码
    private EditText reg_et_sms;
    //验证码按钮
    private Button reg_btn_getsms;
    //注册
    private Button reg_btn_regisered;
    //标记
    private int timer = 60 ;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constants.HANDLER_WHAT_TIME_DOWN:
                    if(timer > 0){
                        timer--;
                        reg_btn_getsms.setText(timer + "s");
                        handler.sendEmptyMessageDelayed(Constants.HANDLER_WHAT_TIME_DOWN,1000);
                    }else {
                        //倒计时完成
                        reg_btn_getsms.setEnabled(true);
                        reg_btn_getsms.setText("再次获取");
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        initView();
    }

    //初始化View
    private void initView() {
        reg_et_phone = (EditText) findViewById(R.id.reg_et_phone);
        reg_et_sms = (EditText) findViewById(R.id.reg_et_sms);
        reg_btn_getsms = (Button) findViewById(R.id.reg_btn_getsms);
        reg_btn_getsms.setOnClickListener(this);
        reg_btn_regisered = (Button) findViewById(R.id.reg_btn_regisered);
        reg_btn_regisered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //获取输入框的内容
        String phone = reg_et_phone.getText().toString().trim();
        String sms = reg_et_sms.getText().toString().trim();
        switch (v.getId()){
            case R.id.reg_btn_getsms:
                //判断手机号码是否为空
                if(!TextUtils.isEmpty(phone)){
                    //判断是否是手机号码
                    if(phone.matches(Constants.PHONE_KEY)){
                        //获取验证码
                        BmobSMS.requestSMSCode(phone, "你的验证码，请及时查收", new QueryListener<Integer>() {
                            @Override
                            public void done(Integer integer, BmobException e) {
                                if(e == null){//验证码发送成功
                                    //设置不可点击
                                    reg_btn_getsms.setEnabled(false);
                                    //倒计时
                                    handler.sendEmptyMessage(Constants.HANDLER_WHAT_TIME_DOWN);
                                }else {
                                    //
                                }
                            }
                        });
                    }else {
                        //
                    }
                }else {
                    //
                }
                break;
            case R.id.reg_btn_regisered:

                break;
        }
    }
}
