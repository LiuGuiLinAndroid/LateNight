package com.liuguilin.latenight.activity;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   SetPassWordActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/20 13:26
 *  描述：    设置密码
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.GankUser;
import com.sdsmdg.tastytoast.TastyToast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class SetPassWordActivity extends AppCompatActivity implements View.OnClickListener {

    //返回
    private ImageView icon_black;
    //密码
    private EditText et_pass;
    //确认密码
    private EditText et_password;
    //确定
    private Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_password);

        initView();
    }

    //初始化View
    private void initView() {
        icon_black = (ImageView) findViewById(R.id.icon_black);
        icon_black.setOnClickListener(this);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_black:
                finish();
                break;
            case R.id.btn_confirm:
                String pass = et_pass.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                //不能为空
                if (!TextUtils.isEmpty(pass) & !TextUtils.isEmpty(password)) {
                    //判断是否相同
                    if (pass.equals(password)) {
                        //设置密码
                        GankUser user = new GankUser();
                        BmobUser bmobUser = BmobUser.getCurrentUser();
                        user.update(bmobUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    TastyToast.makeText(SetPassWordActivity.this, "密码设置成功，快去登录吧！", TastyToast.LENGTH_LONG,
                                            TastyToast.SUCCESS);
                                    finish();
                                } else {
                                    TastyToast.makeText(SetPassWordActivity.this, "密码设置失败" + e.toString(), TastyToast.LENGTH_LONG,
                                            TastyToast.ERROR);
                                }
                            }
                        });
                    } else {
                        TastyToast.makeText(this, "两次输入的密码不不一致", TastyToast.LENGTH_LONG, TastyToast.INFO);
                    }
                } else {
                    TastyToast.makeText(this, "输入框不能为空", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                break;
        }
    }
}
