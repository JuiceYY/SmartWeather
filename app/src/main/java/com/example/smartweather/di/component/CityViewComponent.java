package com.example.smartweather.di.component;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 13:26
 * DESCRIPTION:
 */

import com.example.smartweather.di.module.CityPresenterModule;
import com.example.smartweather.presenter.CityPresenter;
import com.example.smartweather.view.fragment.ChooseCityFragment;

import dagger.Component;

@Component(modules = CityPresenterModule.class)
public interface CityViewComponent {

    void inject(ChooseCityFragment fragment);
}
