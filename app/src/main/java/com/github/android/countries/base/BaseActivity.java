package com.github.android.countries.base;

import android.support.v7.app.AppCompatActivity;

import com.github.android.countries.data.DataManager;
import com.github.android.countries.data.api.CountryServices;
import com.github.android.countries.data.api.CountryServicesFactory;

public abstract class BaseActivity extends AppCompatActivity {

    private CountryServices countryServices;
    private DataManager manager;

    public CountryServices getCountryServices() {
        if (countryServices == null) {
            countryServices = CountryServicesFactory.create();
        }
        return countryServices;
    }

    public DataManager getManager() {
        if (manager == null) {
            manager = new DataManager(getCountryServices());
        }
        return manager;
    }
}
