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

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.view.CustomDialog;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherActivity extends BaseActivity {

    //滚动文本
    private TickerView tickerView;
    //提示框
    private CustomDialog autoDialog;
    //定位
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_weather);

        initView();
    }

    //初始化View
    private void initView() {
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);

        initLocation();

        //开启定位
        mLocationClient.start();

        tickerView = (TickerView) findViewById(R.id.tickerView);
        tickerView.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerView.setText("600");
    }

    //初始化定位函数
    private void initLocation() {
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
                    L.i("定位成功");
                    break;
                //网络
                case BDLocation.TypeNetWorkLocation:
                    L.i("定位成功");
                    break;
                //离线
                case BDLocation.TypeOffLineLocation:
                    L.i("定位成功");
                    break;
                //错误
                case BDLocation.TypeServerError:
                case BDLocation.TypeNetWorkException:
                case BDLocation.TypeCriteriaException:
                    L.i("定位失败");
                    break;
            }
        }
    }

}
