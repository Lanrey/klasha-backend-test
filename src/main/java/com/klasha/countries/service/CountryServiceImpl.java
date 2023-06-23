package com.klasha.countries.service;

import com.klasha.countries.model.CountryResponse;
import com.klasha.countries.model.ExchangeRate;
import com.klasha.countries.model.response.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    WebClient webClient;

    @Autowired
    public CountryServiceImpl(WebClient webClient){
        this.webClient = webClient;
    }

    public CountryResponse<Country> getCountryCurrency(String country) {
        return webClient
                .get()
                .uri("/currency/q?country={country}",country)
                .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CountryResponse<Country>>(){})
                .onErrorReturn(new CountryResponse<>(true,"Something went wrong",new Country()))
                .block();
    }
    public CountryResponse<Country> getCountryStates(String country) {
        return webClient
                .get()
                .uri("/states/q?country={country}",country)
                .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CountryResponse<Country>>(){})
                .onErrorReturn(new CountryResponse<>(true,"Something went wrong",new Country()))
                .block();
    }
    public CountryResponse<ArrayList<String>> getCountryCities(String country) {
        return webClient
                .get()
                .uri("/cities/q?country={country}",country)
                .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CountryResponse<ArrayList<String>>>(){})
                .onErrorReturn(new CountryResponse<>(true, "Something went wrong", new ArrayList<>() {
                }))
                .block();
    }
    public CountryResponse<Country> getCountryPopulation(String country) {
        return webClient
                .get()
                .uri("/population/q?country={country}",country)
                .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CountryResponse<Country>>(){})
                .onErrorReturn(new CountryResponse<>(true,"Something went wrong",new Country()))
                .block();
    }
    public CountryResponse<Country> getCountryCapital(String country) {
        return webClient
                .get()
                .uri("/capital/q?country={country}",country)
                .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CountryResponse<Country>>(){})
                .onErrorReturn(new CountryResponse<>(true,"Something went wrong",new Country()))
                .block();
    }
    public CountryResponse<Country> getCountryLocation(String country) {
        return webClient
                .get()
                .uri("/positions/q?country={country}",country)
                .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CountryResponse<Country>>(){})
                .onErrorReturn(new CountryResponse<>(true,"Something went wrong",new Country()))
                .block();
    }
    public CountryResponse<ArrayList<CountryPopulation>> getCitiesPopulationInCountry(String country, int numberOfCities) {
        return webClient
                .get()
                .uri("/population/cities/filter/q?limit={numberOfCities}&order=dsc&orderBy=population&country={country}",numberOfCities,country)
                .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CountryResponse<ArrayList<CountryPopulation>>>(){})
                .onErrorReturn(new CountryResponse<>(true,"Something went wrong", new ArrayList<>()))
                .block();
    }
    public ExchangeRate getExchangeRate(String sourceCurrency, String targetCurrency) {
        var inputStream = getClass().getClassLoader().getResourceAsStream("exchange_rate.csv");
        try(Reader reader = new BufferedReader(new InputStreamReader(inputStream))){
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(ExchangeRate.class)
                    .withSeparator(',')
                    .build();
            var exchangeRates = (List<ExchangeRate>) csvToBean.parse();
            var exchangeRate = exchangeRates.stream().filter(c -> c.getSourceCurrency().equals(sourceCurrency) && c.getTargetCurrency().equals(targetCurrency)).findFirst();
            return exchangeRate.orElse(new ExchangeRate("NaN","NaN",0));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Exchange getCountryCurrency(String country, String targetCurrency, double amount) {
        var countryCurrency = getCountryCurrency(country);
        var exchangeRate = getExchangeRate(countryCurrency.getData().getCurrency(),targetCurrency);
        var rate = exchangeRate.getRate() * amount;
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return Exchange.builder()
                .currency(countryCurrency.getData().getCurrency())
                .convertedAmount(formatter.format(rate))
                .build();
    }
    @Override
    public CountryDetails getCountryDetails(String country) {
        var population = getCountryPopulation(country);
        var countryCapital = getCountryCapital(country);
        var position = getCountryLocation(country);
        var countryCurrency = getCountryCurrency(country);
        return CountryDetails.builder()
                .currency(countryCurrency.getData().getCurrency())
                .iso2(countryCurrency.getData().getIso2())
                .iso3(countryCurrency.getData().getIso3())
                .lat(position.getData().getLat())
                .longitude(position.getData().getLongitude())
                .capital(countryCapital.getData().getCapital())
                .populationCounts(population.getData().getPopulationCounts())
                .build();
    }

    @Override
    public StateAndCity getStatesAndCitiesOfCountry(String country) {
        var states = getCountryStates(country);
        var cities = getCountryCities(country);
        return StateAndCity.builder()
                .states(states.getData().getStates())
                .cities(cities.getData())
                .build();
    }

    @Override
    public CombinedCountryPopulation getCitiesPopulationInCountry(int numberOfCities) {
        var italy = getCitiesPopulationInCountry("Italy",numberOfCities);
        var newZealand = getCitiesPopulationInCountry("New Zealand",numberOfCities);
        var ghana = getCitiesPopulationInCountry("Ghana",numberOfCities);
        return CombinedCountryPopulation
                .builder()
                .newZealand(newZealand.getData())
                .ghana(ghana.getData())
                .italy(italy.getData())
                .build();
    }
}
