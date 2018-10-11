package com.github.android.countries.data;

import com.github.android.countries.data.api.CountryServices;
import com.github.android.countries.data.entity.Country;

import java.util.List;

import io.reactivex.Observable;

public class DataManager {

    private CountryServices countryServices;

    public DataManager(CountryServices countryServices) {
        this.countryServices = countryServices;
    }

    public Observable<List<Country>> findAll() {
        return countryServices.findAll();
    }
}
