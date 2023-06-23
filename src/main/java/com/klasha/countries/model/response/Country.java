package com.klasha.countries.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country {
    private String name;
    private String lat;
    @JsonProperty("long")
    private String longitude;
    private String capital;
    private String currency;
    private String iso2;
    private String iso3;
    private ArrayList<State> states;
    private ArrayList<Population> populationCounts;
}
