package com.github.android.countries.ui;

import com.github.android.countries.TestDataFactory;
import com.github.android.countries.data.DataManager;
import com.github.android.countries.data.entity.Country;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {

    @Mock
    public DataManager manager;

    @Mock
    public MainView view;

    private MainPresenter presenter;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        Scheduler testScheduler = Schedulers.trampoline();
        presenter = new MainPresenter(manager, testScheduler, testScheduler);
        presenter.attachView(view);
    }

    @Test
    public void findAll_withSuccess() {

        List<Country> mockCountryList = TestDataFactory.makeDataCountryList(10);

        when(manager.findAll())
                .thenReturn(Observable.just(mockCountryList));

        presenter.findAll();

        verify(view).showCountryAll(mockCountryList);
    }

    @Test
    public void findAll_withError() {

        Throwable mockError = new Throwable("Error");

        when(manager.findAll())
                .thenReturn(Observable.error(mockError));

        presenter.findAll();

        verify(view).showError(mockError);
    }

    @After
    public void tearDown() {

        presenter.detachView();
    }
}