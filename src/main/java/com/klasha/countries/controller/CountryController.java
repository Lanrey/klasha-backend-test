package com.klasha.countries.controller;

import com.klasha.countries.model.Response;
import com.klasha.countries.model.response.CombinedCountryPopulation;
import com.klasha.countries.model.response.CountryDetails;
import com.klasha.countries.model.response.Exchange;
import com.klasha.countries.model.response.StateAndCity;
import com.klasha.countries.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @GetMapping("number-of-cities")
    @Operation(summary = "Retrieve cities in order of population (descending) of Italy, New Zealand and Ghana, where numberOfCities is a query parameter passed in the query.")
    public ResponseEntity<Response<CombinedCountryPopulation>> getNumberOfCities(@RequestParam int numberOfCities) {
        try {
            return ResponseEntity.ok(new Response<>(countryService.getCitiesPopulationInCountry(numberOfCities), true, "Operation successful"));
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(new Response<>(null, false, "Operation unsuccessful"));
        }
    }

    @GetMapping("get-country-information")
    @Operation(summary = "The endpoint that takes a country as a parameter (e.g. Italy, Nigeria, …) and returns the population, capital city, location, currency and ISO 2 and 3")
    public ResponseEntity< Response<CountryDetails>> getCountryInformation(@RequestParam String country) {
        try {
            return ResponseEntity.ok(new Response<>(countryService.getCountryDetails(country), true, "Operation successful"));
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(new Response<>(null, false, "Operation unsuccessful"));
        }
    }

    @GetMapping("get-list-of-states-and-cities")
    @Operation(summary = "The endpoint takes a country as a parameter (e.g. Italy, Nigeria, …) and returns the full list of all the states in the country and all the cities in each state.")
    public ResponseEntity<Response<StateAndCity>> getFullListOfStatesAndCities(@RequestParam String country) {
        try {
            return ResponseEntity.ok(new Response<>(countryService.getStatesAndCitiesOfCountry(country), true, "Operation successful"));
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(new Response<>(null, false, "Operation unsuccessful"));
        }
    }

    @GetMapping("get-currency")
    @Operation(summary = "The endpoint takes a country as a parameter (e.g. Italy, Nigeria, …), a monetary amount and a target currency and provides the country currency and converts the amount to the target currency")
    public ResponseEntity<Response<Exchange>> getCountryCurrency(@RequestParam String country, @RequestParam double amount, @RequestParam String targetCurrency) {
        try {
            return ResponseEntity.ok(new Response<>(countryService.getCountryCurrency(country,targetCurrency,amount), true, "Operation successful"));
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(new Response<>(null, false, "Operation unsuccessful"));
        }
    }
}
