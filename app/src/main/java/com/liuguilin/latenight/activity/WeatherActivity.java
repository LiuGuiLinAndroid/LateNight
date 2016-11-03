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
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.view.RiseNumberTextView;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherActivity extends BaseActivity implements View.OnClickListener {

    //定位
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    //滚动文本
    private RiseNumberTextView numberTextView;
    //刷新按钮
    private Button btn_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_weather);

        initLocation();
        initView();

    }

    //初始化View
    private void initView() {
        numberTextView = (RiseNumberTextView) findViewById(R.id.mRiseNumberTextView);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);
        //startNumberAutoUp(numberTextView, 55);
        //开启定位
        mLocationClient.start();
    }

    //初始化定位函数
    private void initLocation() {
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
            JSONObject jsonObjectResult = jsonObject.getJSONObject("result");
            JSONObject jsonObjectData = jsonObjectResult.getJSONObject("data");
            JSONObject jsonObjectRealtime = jsonObjectData.getJSONObject("realtime");
            //更新时间
            jsonObjectRealtime.getString("time");
            //农历
            jsonObjectRealtime.getString("moon");
            JSONObject jsonObjectWeather = jsonObjectRealtime.getJSONObject("weather");
            //天气
            jsonObjectWeather.getString("info");
            //图片ID
            jsonObjectWeather.getString("img");
            JSONObject jsonObjectWind = jsonObjectRealtime.getJSONObject("wind");
            //风向
            jsonObjectWind.getString("direct");
            //风力
            jsonObjectWind.getString("power");
            JSONObject jsonObjectInfo = jsonObjectData.getJSONObject("info");
            //穿衣
            jsonObjectInfo.getJSONArray("chuanyi");
            //感冒
            jsonObjectInfo.getJSONArray("ganmao");
            //空调
            jsonObjectInfo.getJSONArray("kongtiao");
            //洗车
            jsonObjectInfo.getJSONArray("xiche");
            //运动
            jsonObjectInfo.getJSONArray("yundong");
            //紫外线
            jsonObjectInfo.getJSONArray("ziwaixian");

            //一周天气
            jsonObjectData.getJSONArray("weather");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_refresh:
                initLocation();
                break;
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
                    getNewWeather(location.getCity());
                    break;
                //错误
                case BDLocation.TypeServerError:
                case BDLocation.TypeNetWorkException:
                case BDLocation.TypeCriteriaException:
                    getNewWeather("深圳");
                    break;
            }
        }
    }
}
