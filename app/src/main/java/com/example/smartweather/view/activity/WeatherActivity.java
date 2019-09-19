package com.example.smartweather.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartweather.R;
import com.example.smartweather.base.Constant;
import com.example.smartweather.contract.WeatherContract;
import com.example.smartweather.data.bean.AQI;
import com.example.smartweather.data.bean.Basic;
import com.example.smartweather.data.bean.Forecast;
import com.example.smartweather.data.bean.Now;
import com.example.smartweather.data.bean.Suggestion;
import com.example.smartweather.di.component.DaggerWeatherActivityComponent;
import com.example.smartweather.di.module.WeatherPresenterModule;
import com.example.smartweather.view.fragment.ChooseCityFragment;

import java.util.List;

import javax.inject.Inject;


public class WeatherActivity extends AppCompatActivity implements WeatherContract.View{

    /**
     * UI
     */
    private ScrollView mWeatherLayout;
    private TextView mTitleCity;
    private TextView mTitleUpdateTime;
    private TextView mDegreeText;
    private TextView mWeatherInfoText;
    private LinearLayout mForecastLayout;
    private TextView mAqiText;
    private TextView mPm25Text;
    private TextView mComfortText;
    private TextView mCarWashText;
    private TextView mSportText;
    private ImageView mBingPicImg;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    public DrawerLayout mDrawerLayout;
    private Button mNavButton;
    private Button mSettingButton;
    private ProgressDialog mDialog;

    private String mWeatherId; //记录城市id

    @Inject
    WeatherContract.Presenter mPresenter;

    public WeatherContract.Presenter getPresenter(){
        return mPresenter;
    }

    public void setWeatherId(String weatherId){
        this.mWeatherId = weatherId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        DaggerWeatherActivityComponent.builder()
                .weatherPresenterModule(new WeatherPresenterModule())
                .build()
                .inject(this);

        mPresenter.takeView(this);

        initData();
        initView();

    }

    private void initView() {
        //状态栏沉浸
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        mWeatherLayout =  findViewById(R.id.weather_layout);
        mTitleCity =  findViewById(R.id.title_city);
        mTitleUpdateTime = findViewById(R.id.title_update_time);
        mDegreeText = findViewById(R.id.degree_text);
        mWeatherInfoText = findViewById(R.id.weather_info_text);
        mForecastLayout =  findViewById(R.id.forecast_layout);
        mAqiText = findViewById(R.id.aqi_text);
        mPm25Text = findViewById(R.id.pm25_text);
        mComfortText = findViewById(R.id.comfort_text);
        mCarWashText = findViewById(R.id.car_wash_text);
        mSportText = findViewById(R.id.sport_text);
        mBingPicImg = findViewById(R.id.bing_pic_img);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavButton = findViewById(R.id.nav_button);
        mSettingButton = findViewById(R.id.setting_button);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);

        //下拉刷新
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.queryWeather(mWeatherId);
                mPresenter.getBackgroundPic();
            }
        });
        //选择城市
        mNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WeatherActivity.this, "设置", Toast.LENGTH_SHORT).show();
            }
        });
        //加载背景图
        mPresenter.getBackgroundPic();
    }

    private void initData() {
        Intent intent = getIntent();
        mWeatherId = intent.getStringExtra(Constant.SHARED_WEATHER_ID);
        if(mWeatherId != null){
            mPresenter.queryWeather(mWeatherId);
        }else{
            showError("没有城市信息");
        }
    }


    @Override
    public void showWeather() {
        mWeatherLayout.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showBasic(Basic basic) {
        mTitleCity.setText(basic.getCity());

    }

    @Override
    public void showNow(Now now) {
        mDegreeText.setText(now.getTmp()+"℃");
        mWeatherInfoText.setText(now.getCond().getTxt());
    }

    @Override
    public void showForecast(List<Forecast> forecastList) {
        mForecastLayout.removeAllViews();
        for (Forecast forecast  : forecastList){
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, mForecastLayout, false);
            TextView dateText = (TextView)view.findViewById(R.id.date_text);
            TextView infoText = (TextView)view.findViewById(R.id.info_text);
            TextView maxText = (TextView)view.findViewById(R.id.max_text);
            TextView minText = (TextView)view.findViewById(R.id.min_text);
            dateText.setText(forecast.getDate());
            infoText.setText(forecast.getCond().getInfo());
            maxText.setText(forecast.getTmp().getMax());
            minText.setText(forecast.getTmp().getMin());
            mForecastLayout.addView(view);
        }
    }

    @Override
    public void showAqi(AQI aqi) {
        if(aqi!= null){
            mAqiText.setText(aqi.getCity().getAqi());
            mPm25Text.setText(aqi.getCity().getPm25());
        }
    }

    @Override
    public void showSuggestion(Suggestion suggestion) {
        mComfortText.setText("舒适度: " + suggestion.getComfort().getInfo());
        mCarWashText.setText("洗车指数: " + suggestion.getCarWash().getInfo());
        mSportText.setText("运动建议: " + suggestion.getSport().getInfo());
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showBackgroundPic(String picUrl) {
        Glide.with(this).load(picUrl).into(mBingPicImg);
    }

    @Override
    public void showLoading() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("正在加载");
        mDialog.setCancelable(false);
        mDialog.show();
    }

    @Override
    public void closeLoading() {
        mDialog.dismiss();
    }

}
