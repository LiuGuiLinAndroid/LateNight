package com.liuguilin.latenight.activity;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   LoginActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/18 16:42
 *  描述：    登录
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liuguilin.latenight.MainActivity;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //声明
    private TextView login_terms;
    //登录
    private Button login;
    //免费注册
    private TextView login_registered;
    //忘记密码
    private TextView login_forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }
    //初始化View
    private void initView() {
        login_terms = (TextView) findViewById(R.id.login_terms);
        //设置下划线
        Constants.setHtml(login_terms);
        login_terms.setOnClickListener(this);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        login_registered = (TextView) findViewById(R.id.login_registered);
        login_registered.setOnClickListener(this);
        login_forget = (TextView) findViewById(R.id.login_forget);
        login_forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_terms:
                startActivity(new Intent(this,TermsActivity.class));
                break;
            case R.id.login:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.login_registered:
                startActivity(new Intent(this,RegisteredActivity.class));
                break;
            case R.id.login_forget:
                startActivity(new Intent(this,ForgetActivity.class));
                break;
        }
    }
}
