package com.github.android.countries;

import android.app.Application;

import timber.log.Timber;

public class BaseApplication extends Application {

    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        Timber.plant(new Timber.DebugTree());
    }

    public static Application get() {
        return application;
    }
}
