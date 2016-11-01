package com.liuguilin.latenight.activity;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   ForgetActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/18 23:04
 *  描述：    忘记密码
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import cn.bmob.v3.listener.UpdateListener;

public class ForgetActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_sms;
    private ImageView iv_phone_clear;
    private Button btn_getsms;
    private Button btn_confirm;
    private EditText et_pass;
    private EditText et_password;
    //标记
    private int timer = 60;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.HANDLER_WHAT_TIME_DOWN:
                    if (timer > 0) {
                        timer--;
                        btn_getsms.setText(timer + "s");
                        handler.sendEmptyMessageDelayed(Constants.HANDLER_WHAT_TIME_DOWN, 1000);
                    } else {
                        timer = 60;
                        //倒计时完成
                        btn_getsms.setEnabled(true);
                        btn_getsms.setText("再次获取");
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        initView();
    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_sms = (EditText) findViewById(R.id.et_sms);
        iv_phone_clear = (ImageView) findViewById(R.id.iv_phone_clear);
        iv_phone_clear.setVisibility(View.GONE);
        iv_phone_clear.setOnClickListener(this);
        btn_getsms = (Button) findViewById(R.id.btn_getsms);
        btn_getsms.setOnClickListener(this);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);

        //输入框监听
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                   iv_phone_clear.setVisibility(View.VISIBLE);
                } else {
                    iv_phone_clear.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        String phone = et_phone.getText().toString().trim();
        String sms = et_sms.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        switch (v.getId()){
            case R.id.iv_phone_clear:
                et_phone.setText("");
                et_sms.setText("");
                break;
            case R.id.btn_getsms:
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
                                    TastyToast.makeText(ForgetActivity.this, "短信验证码已发送", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                                    //设置不可点击
                                    btn_getsms.setEnabled(false);
                                    //倒计时
                                    handler.sendEmptyMessage(Constants.HANDLER_WHAT_TIME_DOWN);
                                } else {
                                    TastyToast.makeText(ForgetActivity.this, "短信验证码发送失败" + e.toString(), TastyToast.LENGTH_LONG, TastyToast.ERROR);
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
            case R.id.btn_confirm:
                if (!TextUtils.isEmpty(phone) & !TextUtils.isEmpty(pass) & !TextUtils.isEmpty(password) ) {
                    if (!TextUtils.isEmpty(sms)) {
                        if (PhoneFormatCheckUtils.isChinaPhoneLegal(phone)) {
                            if(pass.equals(password)) {
                               //重置
                                GankUser.resetPasswordBySMSCode(sms,password, new UpdateListener() {

                                    @Override
                                    public void done(BmobException ex) {
                                        if(ex==null){
                                            TastyToast.makeText(ForgetActivity.this, "密码重置成功", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                                            finish();
                                        }else{
                                            TastyToast.makeText(ForgetActivity.this, "重置失败：code ="+ex.getErrorCode(), TastyToast.LENGTH_LONG,
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
