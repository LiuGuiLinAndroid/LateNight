package com.liuguilin.latenight.activity;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   WeatherActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/24 21:09
 *  描述：    天气
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.util.SharePreUtils;
import com.liuguilin.latenight.view.CustomDialog;
import com.liuguilin.latenight.view.RiseNumberTextView;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherActivity extends BaseActivity {

    //提示框
    private CustomDialog autoDialog;
    //定位
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    //滚动文本
    private RiseNumberTextView numberTextView;
    //定位进度
    private ProgressBar progressBar;
    //定位结果
    private TextView loading_location;
    //定位城市
    private String city = "深圳";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.HANDLER_WHAT_INIT_WEATHER:
                    autoDialog.dismiss();
                    initView();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_weather);

        //有城市
        if (!TextUtils.isEmpty(SharePreUtils.getString(this, "auto_city", ""))) {
            initView();
        } else {
            initLocation();
        }
    }

    //初始化View
    private void initView() {
        numberTextView = (RiseNumberTextView) findViewById(R.id.mRiseNumberTextView);
        //startNumberAutoUp(numberTextView, 55);
        getNewWeather(city);
    }

    //初始化定位函数
    private void initLocation() {
        //进度加载
        autoDialog = new CustomDialog(this, 80, 80, R.layout.dialog_location, R.style.pop_anim_style, Gravity.CENTER);
        progressBar = (ProgressBar) autoDialog.findViewById(R.id.progressBar);
        loading_location = (TextView) autoDialog.findViewById(R.id.loading_location);
        autoDialog.setCancelable(false);
        autoDialog.show();

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        int span = 1;
        option.setScanSpan(span);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
        //开启定位
        mLocationClient.start();
        L.i("开启定位");
    }

    //开始执行
    private void startNumberAutoUp(RiseNumberTextView view, int number) {
        view.withNumber(number);
        // 设置动画播放时间
        view.setDuration(1500);
        // 开始播放动画
        view.start();
    }

    //获取天气
    private void getNewWeather(String city) {
        String weather_url = "http://op.juhe.cn/onebox/weather/query?cityname=" + city + "&key=4ea58de8a7573377cec0046f5e2469d5";
        RxVolley.get(weather_url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i(t.toString());
                parsingJson(t);
            }

            @Override
            public void onFailure(VolleyError error) {
                L.e("error:" + error.toString());
            }
        });
    }

    //解析Json
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //定位回调
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            switch (location.getLocType()) {
                //GPS
                case BDLocation.TypeGpsLocation:
                case BDLocation.TypeNetWorkLocation:
                case BDLocation.TypeOffLineLocation:
                    city = location.getCity();
                    loading_location.setText("定位成功：" + city);
                    SharePreUtils.putString(WeatherActivity.this, "auto_city", city);
                    handler.sendEmptyMessageDelayed(Constants.HANDLER_WHAT_INIT_WEATHER, 1500);
                    break;
                //错误
                case BDLocation.TypeServerError:
                case BDLocation.TypeNetWorkException:
                case BDLocation.TypeCriteriaException:
                    loading_location.setText("定位失败");
                    break;
            }
        }
    }
}
