package com.liuguilin.latenight.activity;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   RegisteredActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/18 16:44
 *  描述：    注册
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.entity.GankUser;
import com.liuguilin.latenight.util.PhoneFormatCheckUtils;
import com.sdsmdg.tastytoast.TastyToast;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class RegisteredActivity extends AppCompatActivity implements View.OnClickListener {

    //电话号码
    private EditText reg_et_phone;
    //验证码
    private EditText reg_et_sms;
    //验证码按钮
    private Button reg_btn_getsms;
    //注册
    private Button reg_btn_regisered;
    //清除
    private ImageView reg_iv_phone_clear;
    //标记
    private int timer = 60;
    //返回
    private ImageView icon_black;
    //密码
    private EditText et_pass;
    //确认密码
    private EditText et_password;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.HANDLER_WHAT_TIME_DOWN:
                    if (timer > 0) {
                        timer--;
                        reg_btn_getsms.setText(timer + "s");
                        handler.sendEmptyMessageDelayed(Constants.HANDLER_WHAT_TIME_DOWN, 1000);
                    } else {
                        timer = 60;
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
        reg_iv_phone_clear = (ImageView) findViewById(R.id.reg_iv_phone_clear);
        reg_iv_phone_clear.setVisibility(View.GONE);
        reg_iv_phone_clear.setOnClickListener(this);
        reg_btn_getsms.setOnClickListener(this);
        reg_btn_regisered = (Button) findViewById(R.id.reg_btn_regisered);
        reg_btn_regisered.setOnClickListener(this);
        icon_black = (ImageView) findViewById(R.id.icon_black);
        icon_black.setOnClickListener(this);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);


        //输入框的监听
        reg_et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    reg_iv_phone_clear.setVisibility(View.VISIBLE);
                } else {
                    reg_iv_phone_clear.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        //获取输入框的内容
        String phone = reg_et_phone.getText().toString().trim();
        String sms = reg_et_sms.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        switch (v.getId()) {
            case R.id.icon_black:
                finish();
                break;
            case R.id.reg_iv_phone_clear:
                reg_et_phone.setText("");
                reg_et_sms.setText("");
                et_pass.setText("");
                et_password.setText("");
                reg_iv_phone_clear.setVisibility(View.GONE);
                break;
            case R.id.reg_btn_getsms:
                //判断手机号码是否为空
                if (!TextUtils.isEmpty(phone)) {
                    //判断是否是手机号码
                    if (PhoneFormatCheckUtils.isChinaPhoneLegal(phone)) {
                        //获取验证码
                        BmobSMS.requestSMSCode(phone, "你的验证码，请及时查收", new QueryListener<Integer>() {
                            @Override
                            public void done(Integer integer, BmobException e) {
                                if (e == null) {
                                    //验证码发送成功
                                    TastyToast.makeText(RegisteredActivity.this, "短信验证码已发送", TastyToast.LENGTH_LONG,
                                            TastyToast.SUCCESS);
                                    //设置不可点击
                                    reg_btn_getsms.setEnabled(false);
                                    //倒计时
                                    handler.sendEmptyMessage(Constants.HANDLER_WHAT_TIME_DOWN);
                                } else {
                                    TastyToast.makeText(RegisteredActivity.this, "短信验证码发送失败" + e.toString(), TastyToast.LENGTH_LONG, TastyToast.ERROR);
                                }
                            }
                        });
                    } else {
                        TastyToast.makeText(this, "请输入正确的手机号码", TastyToast.LENGTH_LONG, TastyToast.INFO);
                    }
                } else {
                    TastyToast.makeText(this, "输入框不能为空", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                break;
            case R.id.reg_btn_regisered:
                if (!TextUtils.isEmpty(phone) & !TextUtils.isEmpty(pass) & !TextUtils.isEmpty(password) ) {
                    if (!TextUtils.isEmpty(sms)) {
                        if (PhoneFormatCheckUtils.isChinaPhoneLegal(phone)) {
                            if(pass.equals(password)) {
                                //注册
                                GankUser user = new GankUser();
                                user.setPassword(password);
                                user.setMobilePhoneNumber(phone);
                                user.signOrLogin(sms, new SaveListener<GankUser>() {
                                    @Override
                                    public void done(GankUser gankUser, BmobException e) {
                                        if (e == null) {
                                            TastyToast.makeText(RegisteredActivity.this, "注冊成功", TastyToast.LENGTH_LONG,
                                                    TastyToast.SUCCESS);
                                            finish();
                                        } else {
                                            TastyToast.makeText(RegisteredActivity.this, "注冊失败" + e.toString(), TastyToast.LENGTH_LONG,
                                                    TastyToast.ERROR);
                                        }
                                    }
                                });
                            }else {
                                TastyToast.makeText(this, "两次输入的密码不不一致", TastyToast.LENGTH_LONG, TastyToast.INFO);
                            }
                        } else {
                            TastyToast.makeText(this, "请输入11位手机号码", TastyToast.LENGTH_LONG, TastyToast.INFO);
                        }
                    } else {
                        TastyToast.makeText(this, "验证码不能为空", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    }
                } else {
                    TastyToast.makeText(this, "手机号码不能为空", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                break;
        }
    }
}
