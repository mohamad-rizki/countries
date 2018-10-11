package com.github.android.countries.base;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T extends BaseView> implements Presenter<T> {

    protected CompositeDisposable disposable;

    private T view;

    @Override
    public void attachView(T view) {
        this.view = view;
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        this.view = null;
        this.disposable.clear();
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    protected T getView() {
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new BaseViewNotAttachedException();
    }

    protected static class BaseViewNotAttachedException extends RuntimeException {

        BaseViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
