package com.example.smartweather.di.component;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 13:34
 * DESCRIPTION:
 */

import com.example.smartweather.di.module.CityModelModule;
import com.example.smartweather.di.scope.CityModelScope;
import com.example.smartweather.presenter.CityPresenter;

import dagger.Component;

@CityModelScope
@Component(dependencies = ApplicationComponent.class, modules = CityModelModule.class)
public interface CityPresenterComponent {

    void inject(CityPresenter presenter);
}
