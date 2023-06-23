package com.klasha.countries.service;

import com.klasha.countries.model.response.CombinedCountryPopulation;
import com.klasha.countries.model.response.CountryDetails;
import com.klasha.countries.model.response.Exchange;
import com.klasha.countries.model.response.StateAndCity;

public interface CountryService {
    Exchange getCountryCurrency(String country, String targetCurrency, double amount);
    CountryDetails getCountryDetails(String country);
    StateAndCity getStatesAndCitiesOfCountry(String country);
    CombinedCountryPopulation getCitiesPopulationInCountry(int numberOfCities);
}
