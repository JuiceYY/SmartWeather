package com.example.smartweather.view.fragment;

import android.os.Bundle;
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
import com.example.smartweather.contract.CityContract;

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

    /**
     * Constant
     */
    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTY = 2;
    private int currentLevel;

    @Inject
    CityContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);

        initView(view);

        mPresenter.takeView(this);
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

        currentLevel = LEVEL_PROVINCE;

        mPresenter.queryProvinces();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    mPresenter.queryCities(position);
                } else if (currentLevel == LEVEL_CITY) {
                    mPresenter.queryCounties(position);
                } else if (currentLevel == LEVEL_COUNTY) {
                    //从城市列表跳转到天气界面
                    mPresenter.startWeatherActivity(position);
                }
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.queryProvinces();
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

}
