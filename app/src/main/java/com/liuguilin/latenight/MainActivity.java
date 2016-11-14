package com.liuguilin.latenight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.activity.AndroidActivity;
import com.liuguilin.latenight.activity.AppActivity;
import com.liuguilin.latenight.activity.GirlActivity;
import com.liuguilin.latenight.activity.IOSActivity;
import com.liuguilin.latenight.activity.JokeActivity;
import com.liuguilin.latenight.activity.LoginActivity;
import com.liuguilin.latenight.activity.MovieActivity;
import com.liuguilin.latenight.activity.MusicActivity;
import com.liuguilin.latenight.activity.NovelActivity;
import com.liuguilin.latenight.activity.ONEActivity;
import com.liuguilin.latenight.activity.ReadingActivity;
import com.liuguilin.latenight.activity.SettingActivity;
import com.liuguilin.latenight.activity.SmallVideoActivity;
import com.liuguilin.latenight.activity.UserActivity;
import com.liuguilin.latenight.activity.WeatherActivity;
import com.liuguilin.latenight.activity.WebActivity;
import com.liuguilin.latenight.adapter.HorizontalPagerAdapter;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.entity.GankUser;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.util.SharePreUtils;
import com.sdsmdg.tastytoast.TastyToast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {

    //launcher卡片
    private HorizontalInfiniteCycleViewPager infiniteCycleViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    //初始化View
    private void initView() {
        infiniteCycleViewPager = (HorizontalInfiniteCycleViewPager) findViewById(R.id.hicvp);
        HorizontalPagerAdapter horizontalPagerAdapter = new HorizontalPagerAdapter(this, false);
        infiniteCycleViewPager.setAdapter(horizontalPagerAdapter);


        horizontalPagerAdapter.setOnPagerItemClickListener(new HorizontalPagerAdapter.PagerItemClickListener() {
            @Override
            public void onPagerItemClickListener(View view, int postion) {
                L.i("position:" + postion);
                switch (postion) {
                    //天气
                    case 0:
                        startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                        break;
                    //用户
                    case 1:
                        startActivity(new Intent(MainActivity.this, UserActivity.class));
                        break;
                    //ONE
                    case 2:
                        startActivity(new Intent(MainActivity.this, ONEActivity.class));
                        break;
                    //阅读
                    case 3:
                        startActivity(new Intent(MainActivity.this, ReadingActivity.class));
                        break;
                    //Android
                    case 4:
                        startActivity(new Intent(MainActivity.this, AndroidActivity.class));
                        break;
                    //IOS
                    case 5:
                        startActivity(new Intent(MainActivity.this, IOSActivity.class));
                        break;
                    //妹子
                    case 6:
                        startActivity(new Intent(MainActivity.this, GirlActivity.class));
                        break;
                    //小电影
                    case 7:
                        startActivity(new Intent(MainActivity.this, SmallVideoActivity.class));
                        break;
                    //前端
                    case 8:
                        startActivity(new Intent(MainActivity.this, WebActivity.class));
                        break;
                    //音乐
                    case 9:
                        startActivity(new Intent(MainActivity.this, MusicActivity.class));
                        break;
                    //电影
                    case 10:
                        startActivity(new Intent(MainActivity.this, MovieActivity.class));
                        break;
                    //小说
                    case 11:
                        startActivity(new Intent(MainActivity.this, NovelActivity.class));
                        break;
                    //笑话
                    case 12:
                        startActivity(new Intent(MainActivity.this, JokeActivity.class));
                        break;
                    //App
                    case 13:
                        startActivity(new Intent(MainActivity.this, AppActivity.class));
                        break;
                    //设置
                    case 14:
                        startActivity(new Intent(MainActivity.this, SettingActivity.class));
                        break;

                }
            }
        });

        boolean isAutoLogin = SharePreUtils.getBoolean(this, Constants.SHARE_AUTO_LOGIN, false);
        if (isAutoLogin) {
            //登录
            String username = SharePreUtils.getString(this, Constants.SHARE_USER_NAME, "");
            String password = SharePreUtils.getString(this, Constants.SHARE_USER_PASSWORD, "");
            GankUser user = new GankUser();
            //....自动登录的逻辑
            user.setUsername(username);
            user.setPassword(password);
            user.login(new SaveListener<GankUser>() {
                @Override
                public void done(GankUser gankUser, BmobException e) {
                    if (e != null) {
                        TastyToast.makeText(MainActivity.this, "自动登录失败", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }
                }
            });
        }

        //更新数据  有可能需要去掉！
        Intent intent = getIntent();
        String first = intent.getStringExtra("first_update_user");
        if (!TextUtils.isEmpty(first)) {
            if (first.equals("update")) {
                updateUser();
            }
        }

    }

    private void updateUser() {
        //获取到学生的信息
        String imagePhoto = SharePreUtils.getString(this, Constants.SHARE_USER_PHOTO, "");
        int age = SharePreUtils.getInt(this, Constants.SHARE_USER_AGE, 18);
        String birthday = SharePreUtils.getString(this, Constants.SHARE_USER_BIRTHDAY, "1995-10-5");
        String connstellation = SharePreUtils.getString(this, Constants.SHARE_USER_CONSTELLATION, "天秤座");
        String desc = SharePreUtils.getString(this, Constants.SHARE_USER_DESC, "这个人很懒，什么都没有留下");
        String height = SharePreUtils.getString(this, Constants.SHARE_USER_HEIGHT, "180CM");
        String occupation = SharePreUtils.getString(this, Constants.SHARE_USER_OCCUPATION, "Android软件工程师");
        String school = SharePreUtils.getString(this, Constants.SHARE_USER_SCHOOL, "北京大学");
        boolean sex = SharePreUtils.getBoolean(this, Constants.SHARE_USER_SEX, true);
        String weight = SharePreUtils.getString(this, Constants.SHARE_USER_WEIGHT, "60KG");
        L.i("age:" + age + "birthday:" + birthday + "connstellation:" + connstellation
                + "desc:" + desc + "height:" + height + "occupation:" + occupation + "school:" + school
                + "sex:" + sex + "weight:" + weight);
        GankUser user = new GankUser();
        user.setPhoto(imagePhoto);
        user.setAge(age);
        user.setBirthday(birthday);
        user.setConstellation(connstellation);
        user.setDesc(desc);
        user.setHeight(height);
        user.setOccupation(occupation);
        user.setSchool(school);
        user.setSex(sex);
        user.setWeight(weight);
        BmobUser bmobUser = BmobUser.getCurrentUser();
        user.update(bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e != null) {
                    TastyToast.makeText(MainActivity.this, "更新信息失敗", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        //判断是否在运行
        if (Constants.isServiceRunning(this, "com.liuguilin.latenight.MusicService")) {
            //显示歌曲
        }
    }
}
