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
public class CountryDetails {
    private String lat;
    private String longitude;
    private String capital;
    private String currency;
    private String iso2;
    private String iso3;
    private List<Population> populationCounts;
}
