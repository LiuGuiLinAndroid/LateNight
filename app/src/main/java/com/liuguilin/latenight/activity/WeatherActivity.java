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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.liuguilin.latenight.adapter.WeatherAdapter;
import com.liuguilin.latenight.entity.GraphData;
import com.liuguilin.latenight.entity.WeatherData;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.util.ListViewUtils;
import com.liuguilin.latenight.view.GraphView;
import com.liuguilin.latenight.view.RiseNumberTextView;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends BaseActivity implements View.OnClickListener {

    //定位
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    //滚动文本
    private RiseNumberTextView numberTextView;
    //刷新按钮
    private ImageView iv_refresh;

    private LinearLayout ll_group;
    private TextView tv_update_time;
    private TextView tvRealtime;
    private ImageView iv_weather_img;
    private TextView tv_weather;
    private TextView tv_direct;
    private TextView tv_power;
    private ListView mListView;
    private ProgressBar progressBar;

    private List<WeatherData> mList = new ArrayList<>();
    private WeatherAdapter mAdapter;

    private GraphView mGraphView;
    private ArrayList<GraphData> items = new ArrayList<GraphData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_weather);

        initLocation();
        initView();

    }

    //初始化View
    private void initView() {

        ll_group = (LinearLayout) findViewById(R.id.ll_group);
        tv_update_time = (TextView) findViewById(R.id.tv_update_time);
        tvRealtime = (TextView) findViewById(R.id.tvRealtime);
        iv_weather_img = (ImageView) findViewById(R.id.iv_weather_img);
        tv_weather = (TextView) findViewById(R.id.tv_weather);
        tv_direct = (TextView) findViewById(R.id.tv_direct);
        tv_power = (TextView) findViewById(R.id.tv_power);
        mListView = (ListView) findViewById(R.id.mListView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mGraphView = (GraphView) findViewById(R.id.mGraphView);

        numberTextView = (RiseNumberTextView) findViewById(R.id.mRiseNumberTextView);
        iv_refresh = (ImageView) findViewById(R.id.iv_refresh);
        iv_refresh.setOnClickListener(this);
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
            String time = jsonObjectRealtime.getString("time");
            tv_update_time.setText("上次更新:" + time);
            //农历
            String moon = jsonObjectRealtime.getString("moon");
            tvRealtime.setText(moon);
            JSONObject jsonObjectWeather = jsonObjectRealtime.getJSONObject("weather");

            //温度
            String temperature = jsonObjectWeather.getString("temperature");
            startNumberAutoUp(numberTextView, Integer.parseInt(temperature));

            //天气
            String info = jsonObjectWeather.getString("info");
            tv_weather.setText(info);
            //图片ID
            jsonObjectWeather.getString("img");
            JSONObject jsonObjectWind = jsonObjectRealtime.getJSONObject("wind");
            //风向
            String direct = jsonObjectWind.getString("direct");
            tv_direct.setText("风向:" + direct);
            //风力
            String power = jsonObjectWind.getString("power");
            tv_power.setText("风力:" + power);


            JSONObject jsonObjectLife = jsonObjectData.getJSONObject("life");
            JSONObject jsonObjectInfo = jsonObjectLife.getJSONObject("info");
            //穿衣
            JSONArray jsonArray1 = jsonObjectInfo.getJSONArray("chuanyi");
            addData("穿衣指数", jsonArray1);
            //感冒
            JSONArray jsonArray2 = jsonObjectInfo.getJSONArray("ganmao");
            addData("感冒指数", jsonArray2);
            //空调
            JSONArray jsonArray3 = jsonObjectInfo.getJSONArray("kongtiao");
            addData("空调指数", jsonArray3);
            //洗车
            JSONArray jsonArray4 = jsonObjectInfo.getJSONArray("xiche");
            addData("洗车指数", jsonArray4);
            //运动
            JSONArray jsonArray5 = jsonObjectInfo.getJSONArray("yundong");
            addData("运动指数", jsonArray5);
            //紫外线
            JSONArray jsonArray6 = jsonObjectInfo.getJSONArray("ziwaixian");
            addData("紫外线指数", jsonArray6);

            mAdapter = new WeatherAdapter(this, mList);
            mListView.setAdapter(mAdapter);
            ListViewUtils.setListViewHeightBasedOnChildren(mListView);

            //一周天气
            JSONArray jsonArrayWeather = jsonObjectData.getJSONArray("weather");
            for (int i = 0; i < jsonArrayWeather.length(); i++) {
                JSONObject json = (JSONObject) jsonArrayWeather.get(i);
                JSONObject jsonInfo = json.getJSONObject("info");
                JSONArray jsonArray = jsonInfo.getJSONArray("day");

                switch (i){
                    case 0:
                        GraphData data = new GraphData("今天", jsonArray.getInt(2));
                        items.add(data);
                        break;
                    case 1:
                        GraphData data1 = new GraphData("明天", jsonArray.getInt(2));
                        items.add(data1);
                        break;
                    case 2:
                        GraphData data2 = new GraphData("后天", jsonArray.getInt(2));
                        items.add(data2);
                        break;
                    default:
                        GraphData data3 = new GraphData(json.getString("date"), jsonArray.getInt(2));
                        items.add(data3);
                        break;
                }
            }
            //装载数据
            mGraphView.setItems(items);
            progressBar.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //添加数据
    private void addData(String text, JSONArray jsonArray) throws JSONException {
        WeatherData data = new WeatherData();
        data.setText(text + ":" + jsonArray.getString(0) + "\n" + jsonArray.get(1));
        L.i("指数：" + jsonArray.getString(0) + "," + jsonArray.get(1));
        mList.add(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_refresh:
                mLocationClient.start();
                TastyToast.makeText(this, "正在刷新数据", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                break;
        }
    }

    //定位回调
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            switch (location.getLocType()) {
                //成功
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
