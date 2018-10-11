package com.github.android.countries.base;

public interface Presenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
