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

import com.liuguilin.gankclient.R;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class WeatherActivity extends BaseActivity {

    //滚动文本
    private TickerView tickerView;

    //添加接口
    private String weather_url = "http://op.juhe.cn/onebox/weather/query?cityname=北京&key=4ea58de8a7573377cec0046f5e2469d5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_weather);

        initView();
    }

    //初始化View
    private void initView() {
        tickerView = (TickerView) findViewById(R.id.tickerView);
        tickerView.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerView.setText("600");
    }
}
