package com.klasha.countries.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CombinedCountryPopulation {
    private ArrayList<CountryPopulation> italy;
    private ArrayList<CountryPopulation> newZealand;
    private ArrayList<CountryPopulation> ghana;
}
