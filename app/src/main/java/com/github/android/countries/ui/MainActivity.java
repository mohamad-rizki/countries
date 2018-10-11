package com.github.android.countries.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.android.countries.R;
import com.github.android.countries.adapter.CountryAdapter;
import com.github.android.countries.base.BaseActivity;
import com.github.android.countries.data.entity.Country;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.rv_country)
    RecyclerView rvCountry;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private MainPresenter presenter;

    private List<Country> countryList;
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(
                getManager(), AndroidSchedulers.mainThread(), Schedulers.io());
        presenter.attachView(this);

        countryList = new ArrayList<>();
        adapter = new CountryAdapter(this, countryList);

        rvCountry.setLayoutManager(new LinearLayoutManager(this));
        rvCountry.setItemAnimator(new DefaultItemAnimator());
        rvCountry.setAdapter(adapter);

        loadingData();
    }

    @Override
    public void showCountryAll(List<Country> countryList) {

        this.countryList.clear();
        this.countryList.addAll(countryList);
        adapter.notifyDataSetChanged();
        refresh.setRefreshing(false);
    }

    @Override
    public void showError(Throwable throwable) {

        Timber.w(throwable);
        refresh.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void loadingData() {

        refresh.setRefreshing(true);
        presenter.findAll();
    }
}
