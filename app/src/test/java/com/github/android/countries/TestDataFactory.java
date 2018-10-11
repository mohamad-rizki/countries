package com.github.android.countries;

import com.github.android.countries.data.entity.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestDataFactory {

    public static List<Country> makeDataCountryList(int count) {
        List<Country> countryList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Country country = makeDataCountry();
            countryList.add(country);
        }
        return countryList;
    }

    private static Country makeDataCountry() {
        Country country = new Country();
        country.setName(UUID.randomUUID().toString());
        country.setCapital(UUID.randomUUID().toString());
        country.setRegion(UUID.randomUUID().toString());
        country.setSubRegion(UUID.randomUUID().toString());
        country.setNativeName(UUID.randomUUID().toString());
        country.setCioc(UUID.randomUUID().toString());
        country.setFlag(UUID.randomUUID().toString());
        country.setPopulation(((Double) Math.random()).longValue());
        return country;
    }
}
