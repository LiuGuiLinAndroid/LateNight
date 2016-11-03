package com.liuguilin.latenight.select;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.select
 *  文件名:   SelectConstellationActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/21 16:55
 *  描述：    选择星座
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.MainActivity;
import com.liuguilin.latenight.adapter.ConstellationAdapter;
import com.liuguilin.latenight.entity.GankUser;
import com.liuguilin.latenight.view.CoverFlow;
import com.sdsmdg.tastytoast.TastyToast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class SelectConstellationActivity extends AppCompatActivity implements View.OnClickListener {

    //下一步
    private Button btn_next;
    private CoverFlow mCoverFlow;
    private TextView select_tv_constellation;
    private String[] mStr = {"白羊", "金牛", "双子", "巨蟹", "狮子", "处女", "天秤", "天蝎", "射手", "摩羯", "水瓶", "双鱼"};
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_constellation);

        initView();
    }

    private void initView() {
        select_tv_constellation = (TextView) findViewById(R.id.select_tv_constellation);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        mCoverFlow = (CoverFlow) findViewById(R.id.mCoverFlow);
        mCoverFlow.setSelection(2, true);
        mCoverFlow.setAnimationDuration(1000);
        mCoverFlow.setAdapter(new ConstellationAdapter(this));
        mCoverFlow.setOnItemSelectedListener(selectedListener);

    }

    // 选中图片的监听事件
    AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            select_tv_constellation.setText(mStr[position] + "座");
            flag = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    };

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                updateConstellation();
                break;
        }
    }

    private void updateConstellation() {
        GankUser user = new GankUser();
        user.setConstellation(mStr[flag] + "座");
        BmobUser bmobUser = BmobUser.getCurrentUser();
        user.update(bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    startActivity(new Intent(SelectConstellationActivity.this, MainActivity.class));
                    finish();
                } else {
                    TastyToast.makeText(SelectConstellationActivity.this, "更新信息失敗", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                }
            }
        });
    }
}
