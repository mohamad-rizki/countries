package com.github.android.countries.data.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.github.android.countries.BaseApplication;
import com.github.android.countries.BuildConfig;
import com.github.android.countries.config.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryServicesFactory {

    public static CountryServices create() {
        OkHttpClient client = makeClientService(makeLoggingInterceptor(), makeCache());
        return makeCountryService(client, makeGson());
    }

    private static CountryServices makeCountryService(OkHttpClient okHttp, Gson gson) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.COUNTRY_URL)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(CountryServices.class);
    }

    @NonNull
    private static OkHttpClient makeClientService(
            HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(chain -> {
                    Request.Builder ongoing = chain.request().newBuilder();
                    ongoing.addHeader("Content-Type", "application/json");
                    return chain.proceed(ongoing.build());
                })
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @NonNull
    private static HttpLoggingInterceptor makeLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                        : HttpLoggingInterceptor.Level.NONE);
    }

    @NonNull
    private static Gson makeGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    @NonNull
    private static Cache makeCache() {
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Context context = BaseApplication.get();
        return new Cache(context.getCacheDir(), cacheSize);
    }
}
