package com.example.smartweather.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.smartweather.R;
import com.example.smartweather.base.BaseApplication;
import com.example.smartweather.base.Constant;

import javax.inject.Inject;

public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherId = sp.getString(Constant.SHARED_WEATHER_ID, null);
        if(weatherId != null){
            Intent intent = new Intent(this, WeatherActivity.class);
            intent.putExtra(Constant.SHARED_WEATHER_ID, weatherId);
            startActivity(intent);
            finish();
        }
    }
}
