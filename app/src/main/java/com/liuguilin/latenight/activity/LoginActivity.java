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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.MainActivity;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.entity.GankUser;
import com.liuguilin.latenight.select.SelectPhotoActivity;
import com.liuguilin.latenight.util.SharePreUtils;
import com.liuguilin.latenight.view.CustomDialog;
import com.sdsmdg.tastytoast.TastyToast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //声明
    private TextView login_terms;
    //登录
    private Button btn_login;
    //免费注册
    private TextView login_registered;
    //忘记密码
    private TextView login_forget;
    //用户名
    private EditText login_et_name;
    //密码
    private EditText login_et_password;
    //清除
    private ImageView imageView;
    //密码可见
    private ImageView eye_gone;
    //记住密码
    private CheckBox check_kepp_password;
    //自动登录
    private CheckBox check_auto_login;
    //进度加载
    private CustomDialog dialog;

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
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        login_registered = (TextView) findViewById(R.id.login_registered);
        login_registered.setOnClickListener(this);
        login_forget = (TextView) findViewById(R.id.login_forget);
        login_forget.setOnClickListener(this);
        login_et_name = (EditText) findViewById(R.id.login_et_name);
        login_et_password = (EditText) findViewById(R.id.login_et_password);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setVisibility(View.GONE);
        imageView.setOnClickListener(this);
        eye_gone = (ImageView) findViewById(R.id.eye_gone);
        eye_gone.setVisibility(View.GONE);
        eye_gone.setOnClickListener(this);
        check_kepp_password = (CheckBox) findViewById(R.id.check_kepp_password);
        check_auto_login = (CheckBox) findViewById(R.id.check_auto_login);

        //初始化提示框
        dialog = new CustomDialog(this, 0, 0, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        //屏幕外点击无效
        dialog.setCancelable(false);

        //监听
        login_et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
        });

        login_et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    eye_gone.setVisibility(View.VISIBLE);
                } else {
                    eye_gone.setVisibility(View.GONE);
                }
            }
        });

        //如果选择记住密码
        if (SharePreUtils.getBoolean(this, Constants.SHARE_KEEP_PASSWORD, false)) {
            String username = SharePreUtils.getString(this, Constants.SHARE_USER_NAME, "");
            String password = SharePreUtils.getString(this, Constants.SHARE_USER_PASSWORD, "");
            login_et_name.setText(username);
            login_et_password.setText(password);
        }
        check_auto_login.setChecked(SharePreUtils.getBoolean(this, Constants.SHARE_AUTO_LOGIN, false));
        check_kepp_password.setChecked(SharePreUtils.getBoolean(this, Constants.SHARE_KEEP_PASSWORD, false));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_terms:
                startActivity(new Intent(this, TermsActivity.class));
                break;
            case R.id.btn_login:
                dialog.show();
                String name = login_et_name.getText().toString().trim();
                String password = login_et_password.getText().toString();
                //判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {
                    //登录
                    BmobUser.loginByAccount(name, password, new LogInListener<GankUser>() {
                        @Override
                        public void done(GankUser user, BmobException e) {
                            dialog.dismiss();
                            if (user != null) {
                                TastyToast.makeText(LoginActivity.this, "登录成功", TastyToast.LENGTH_LONG,
                                        TastyToast.SUCCESS);

                                //账号密码我们是一定要保存的，为自定登录做准备
                                SharePreUtils.putString(LoginActivity.this, Constants.SHARE_USER_NAME, login_et_name.getText().toString());
                                SharePreUtils.putString(LoginActivity.this, Constants.SHARE_USER_PASSWORD, login_et_password.getText().toString());

                                //记住密码
                                SharePreUtils.putBoolean(LoginActivity.this, Constants.SHARE_KEEP_PASSWORD, check_kepp_password.isChecked());
                                //自动登录
                                SharePreUtils.putBoolean(LoginActivity.this, Constants.SHARE_AUTO_LOGIN, check_auto_login.isChecked());

                                if (isFirstLogin()) {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                } else {
                                    //引导
                                    startActivity(new Intent(LoginActivity.this, SelectPhotoActivity.class));
                                }
                                finish();
                            } else {
                                TastyToast.makeText(LoginActivity.this, "登录失败" + e.toString(), TastyToast.LENGTH_LONG,
                                        TastyToast.ERROR);
                            }
                        }
                    });
                } else {
                    TastyToast.makeText(this, "输入框不能为空", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                break;
            case R.id.login_registered:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
            case R.id.login_forget:
                startActivity(new Intent(this, ForgetActivity.class));
                break;
            case R.id.imageView:
                login_et_name.setText("");
                login_et_password.setText("");
                imageView.setVisibility(View.GONE);
                break;
            case R.id.eye_gone:
                eye_gone.setSelected(!eye_gone.isSelected());
                if (eye_gone.isSelected()) {
                    login_et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    login_et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }
    }

    //判断是否是第一次登录
    private boolean isFirstLogin() {
        boolean isFirstLogin = SharePreUtils.getBoolean(this, Constants.SHARE_IS_FIRST_LOGIN, false);
        if (isFirstLogin) {
            return true;
        } else {
            SharePreUtils.putBoolean(this, Constants.SHARE_IS_FIRST_LOGIN, true);
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        SharePreUtils.putBoolean(this,Constants.SHARE_AUTO_LOGIN,check_auto_login.isChecked());
//        SharePreUtils.putBoolean(this,Constants.SHARE_KEEP_PASSWORD,check_kepp_password.isChecked());
    }
}
