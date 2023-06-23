package com.klasha.countries.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryPopulation {
    private String city;
    private String country;
    private List<CityPopulation> populationCounts;
}
