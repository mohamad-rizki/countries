package com.github.android.countries.ui;

import com.github.android.countries.base.BasePresenter;
import com.github.android.countries.data.DataManager;

import io.reactivex.Scheduler;

class MainPresenter extends BasePresenter<MainView> {

    private DataManager manager;

    private Scheduler androidScheduler;
    private Scheduler processScheduler;

    MainPresenter(DataManager manager, Scheduler androidScheduler, Scheduler processScheduler) {
        this.manager = manager;
        this.androidScheduler = androidScheduler;
        this.processScheduler = processScheduler;
    }

    void findAll() {
        disposable.add(manager.findAll()
                .observeOn(androidScheduler)
                .subscribeOn(processScheduler)
                .subscribe(countryList -> {
                    if (isViewAttached()) {
                        getView().showCountryAll(countryList);
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        getView().showError(throwable);
                    }
                }));
    }
}
