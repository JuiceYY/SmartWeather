package com.example.smartweather.di.scope;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 13:49
 * DESCRIPTION:
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CityModelScope {
}
