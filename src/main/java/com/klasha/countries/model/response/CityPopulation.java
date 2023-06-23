package com.klasha.countries.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityPopulation {
    private String year;
    private String value;
    private String sex;
    @JsonProperty("reliabilty")
    private String reliability;
}
