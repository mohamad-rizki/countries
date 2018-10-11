package com.github.android.countries.data;

import com.github.android.countries.TestDataFactory;
import com.github.android.countries.data.api.CountryServices;
import com.github.android.countries.data.entity.Country;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

public class DataManagerTest {

    @Mock
    public CountryServices services;

    private DataManager manager;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        manager = new DataManager(services);
    }

    @Test
    public void findAll_withSuccess() {

        List<Country> mockCountryList = TestDataFactory.makeDataCountryList(10);

        when(services.findAll())
                .thenReturn(Observable.just(mockCountryList));

        TestObserver<List<Country>> testObserver = manager.findAll().test();
        testObserver.awaitTerminalEvent();

        testObserver
                .assertNoErrors()
                .assertValue(mockCountryList);
    }

    @Test
    public void findAll_withError() {

        Throwable mockError = new Throwable("Error");

        when(services.findAll())
                .thenReturn(Observable.error(mockError));

        TestObserver<List<Country>> testObserver = manager.findAll().test();
        testObserver.awaitTerminalEvent();

        testObserver
                .assertError(mockError)
                .assertNoValues();
    }

    @After
    public void tearDown() {

        manager = null;
    }
}