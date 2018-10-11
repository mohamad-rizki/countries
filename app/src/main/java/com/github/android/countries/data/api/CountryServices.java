package com.github.android.countries.data.api;

import com.github.android.countries.data.entity.Country;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CountryServices {

    @GET("/rest/v2/all")
    Observable<List<Country>> findAll();
}
