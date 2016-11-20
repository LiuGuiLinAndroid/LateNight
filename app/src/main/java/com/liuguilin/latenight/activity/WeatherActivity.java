package com.liuguilin.latenight.activity;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.activity
 *  文件名:   WeatherActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/24 21:09
 *  描述：    天气
 */

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.adapter.WeatherAdapter;
import com.liuguilin.latenight.entity.WeatherData;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.view.RiseNumberTextView;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends BaseActivity implements View.OnClickListener, OnChartGestureListener, OnChartValueSelectedListener {

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

    //城市
    private TextView tv_city;
    private List<WeatherData> mList = new ArrayList<>();
    private WeatherAdapter mAdapter;

    private String city = "深圳";

    private LineChart mLineChar;
    private ArrayList<Entry> values = new ArrayList<Entry>();
    private LineDataSet set1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_weather);

        initLocation();
        initView();
        initMpAndroid();
    }

    //初始化折线图
    private void initMpAndroid() {
        mLineChar = (LineChart) findViewById(R.id.mLineChar);
        //设置手势滑动事件
        mLineChar.setOnChartGestureListener(this);
        //设置数值选择监听
        mLineChar.setOnChartValueSelectedListener(this);
        //后台绘制
        mLineChar.setDrawGridBackground(false);
        //设置描述文本
        mLineChar.getDescription().setEnabled(false);
        //设置支持触控手势
        mLineChar.setTouchEnabled(true);
        //设置缩放
        mLineChar.setDragEnabled(true);
        //设置推动
        mLineChar.setScaleEnabled(true);
        //如果禁用,扩展可以在x轴和y轴分别完成
        mLineChar.setPinchZoom(true);

        //x轴
        LimitLine llXAxis = new LimitLine(10f, "标记");
        //设置线宽
        llXAxis.setLineWidth(4f);
        //
        llXAxis.enableDashedLine(10f, 10f, 0f);
        //设置
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        XAxis xAxis = mLineChar.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        //设置x轴的最大值
        xAxis.setAxisMaximum(5f);
        //设置x轴的最小值
        xAxis.setAxisMinimum(0f);

        LimitLine ll1 = new LimitLine(30f, "高温");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);

        LimitLine ll2 = new LimitLine(0f, "低温");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);

        YAxis leftAxis = mLineChar.getAxisLeft();
        //重置所有限制线,以避免重叠线
        leftAxis.removeAllLimitLines();
        //设置优秀线
        leftAxis.addLimitLine(ll1);
        //设置及格线
        leftAxis.addLimitLine(ll2);
        //y轴最大
        leftAxis.setAxisMaximum(40f);
        //y轴最小
        leftAxis.setAxisMinimum(-10f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // 限制数据(而不是背后的线条勾勒出了上面)
        leftAxis.setDrawLimitLinesBehindData(true);

        mLineChar.getAxisRight().setEnabled(false);
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
        tv_city = (TextView) findViewById(R.id.tv_city);

        numberTextView = (RiseNumberTextView) findViewById(R.id.mRiseNumberTextView);
        iv_refresh = (ImageView) findViewById(R.id.iv_refresh);
        iv_refresh.setOnClickListener(this);
        //开启定位
        mLocationClient.start();
        L.i("开始定位");
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
        L.i("初始化定位");
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
        tv_city.setText(city);
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
            //ListViewUtils.setListViewHeightBasedOnChildren(mListView);

            //一周天气
            JSONArray jsonArrayWeather = jsonObjectData.getJSONArray("weather");
            for (int i = 0; i < jsonArrayWeather.length(); i++) {
                JSONObject json = (JSONObject) jsonArrayWeather.get(i);
                JSONObject jsonInfo = json.getJSONObject("info");
                JSONArray jsonArray = jsonInfo.getJSONArray("day");

                int code = Integer.parseInt((jsonArray.get(2)).toString());
                L.i("code:" + code);
                values.add(new Entry(i, code));
            }
            //设置数据
            setData(values);
            //默认动画
            mLineChar.animateX(2500);
            //刷新
            //mChart.invalidate();
            // 得到这个文字
            Legend l = mLineChar.getLegend();
            // 修改文字 ...
            l.setForm(Legend.LegendForm.LINE);
            progressBar.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //传递数据集
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setData(ArrayList<Entry> values) {
        if (mLineChar.getData() != null && mLineChar.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChar.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mLineChar.getData().notifyDataChanged();
            mLineChar.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型
            set1 = new LineDataSet(values, "一周天气近况");

            // 在这里设置线
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                // 填充背景只支持18以上
                //Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher);
                //set1.setFillDrawable(drawable);
                set1.setFillColor(0xFF2AA0F7);
            } else {
                set1.setFillColor(Color.BLACK);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            //添加数据集
            dataSets.add(set1);

            //创建一个数据集的数据对象
            LineData data = new LineData(dataSets);

            //谁知数据
            mLineChar.setData(data);
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
                mList.clear();
                values.clear();
                getNewWeather(city);
                TastyToast.makeText(this, "正在刷新数据", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                break;
        }
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

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
                    city = location.getCity();
                    getNewWeather(city);
                    break;
                //错误
                case BDLocation.TypeServerError:
                case BDLocation.TypeNetWorkException:
                case BDLocation.TypeCriteriaException:
                    getNewWeather(city);
                    break;
            }
        }
    }
}
