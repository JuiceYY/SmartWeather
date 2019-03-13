package com.example.smartweather.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartweather.R;
import com.example.smartweather.adapter.ProvinceAdapter;

import com.example.smartweather.base.Constant;
import com.example.smartweather.contract.CityContract;
import com.example.smartweather.di.component.DaggerCityViewComponent;
import com.example.smartweather.di.module.CityPresenterModule;
import com.example.smartweather.view.activity.CityActivity;
import com.example.smartweather.view.activity.WeatherActivity;


import java.util.List;

import javax.inject.Inject;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 18:53
 * DESCRIPTION:
 */

public class ChooseCityFragment extends Fragment implements CityContract.View {

    /**
     * UI
     */
    private ListView mListView;
    private Button mButton;
    private TextView mTextView;
    private ProgressDialog mProgressDialog;

    /**
     * Constant
     */
    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTY = 2;
    private int mCurrentLevel;

    @Inject
    CityContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerCityViewComponent.builder()
                .cityPresenterModule(new CityPresenterModule())
                .build()
                .inject(this);

        mPresenter.takeView(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        mListView = view.findViewById(R.id.list_view);
        mButton = view.findViewById(R.id.back_button);
        mTextView = view.findViewById(R.id.title_text);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCurrentLevel = LEVEL_PROVINCE;

        mPresenter.queryProvinces();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCurrentLevel == LEVEL_PROVINCE) {
                    mPresenter.queryCities(position);
                    mCurrentLevel = LEVEL_CITY;
                } else if (mCurrentLevel == LEVEL_CITY) {
                    mPresenter.queryCounties(position);
                    mCurrentLevel = LEVEL_COUNTY;
                } else if (mCurrentLevel == LEVEL_COUNTY) {
                    //从城市列表跳转到天气界面
                    mPresenter.startWeatherActivity(position);
                }
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.queryProvinces();
                mCurrentLevel = LEVEL_PROVINCE;
            }
        });
    }

    @Override
    public void showNames(List<String> cities) {

        ProvinceAdapter adapter = new ProvinceAdapter(getContext(), cities);
        mListView.setAdapter(adapter);
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startWeatherActivity(String weatherId) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sp.edit().putString(Constant.SHARED_WEATHER_ID, weatherId).apply();
        if(getActivity() instanceof CityActivity){
            Intent intent = new Intent(getActivity(), WeatherActivity.class);
            intent.putExtra(Constant.SHARED_WEATHER_ID, weatherId);
            startActivity(intent);
        }else if(getActivity() instanceof WeatherActivity){
            WeatherActivity weatherActivity = (WeatherActivity) getActivity();
            weatherActivity.setWeatherId(weatherId);
            weatherActivity.mDrawerLayout.closeDrawers();
            weatherActivity.mSwipeRefreshLayout.setRefreshing(true);
            weatherActivity.getPresenter().queryWeather(weatherId);
        }

    }

    @Override
    public void showLoading() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("正在加载");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void closeLoading() {
        mProgressDialog.dismiss();
    }

}
