package com.github.android.countries.ui;

import com.github.android.countries.base.BaseView;
import com.github.android.countries.data.entity.Country;

import java.util.List;

public interface MainView extends BaseView {

    void showCountryAll(List<Country> countryList);
}
